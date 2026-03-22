package io.grpc.internal;

import androidx.core.app.NotificationCompat;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import com.google.common.util.concurrent.MoreExecutors;
import io.grpc.Attributes;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.CompressorRegistry;
import io.grpc.Context;
import io.grpc.DecompressorRegistry;
import io.grpc.InternalDecompressorRegistry;
import io.grpc.InternalStatus;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.SecurityLevel;
import io.grpc.ServerCall;
import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.internal.StreamListener;
import io.perfmark.PerfMark;
import io.perfmark.Tag;
import io.perfmark.TaskCloseable;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes3.dex */
final class ServerCallImpl<ReqT, RespT> extends ServerCall<ReqT, RespT> {
    static final String MISSING_RESPONSE = "Completed without a response";
    static final String TOO_MANY_RESPONSES = "Too many responses";
    private static final Logger log = Logger.getLogger(ServerCallImpl.class.getName());
    private volatile boolean cancelled;
    private boolean closeCalled;
    private Compressor compressor;
    private final CompressorRegistry compressorRegistry;
    private final Context.CancellableContext context;
    private final DecompressorRegistry decompressorRegistry;
    private final byte[] messageAcceptEncoding;
    private boolean messageSent;
    private final MethodDescriptor<ReqT, RespT> method;
    private boolean sendHeadersCalled;
    private CallTracer serverCallTracer;
    private final ServerStream stream;
    private final Tag tag;

    ServerCallImpl(ServerStream serverStream, MethodDescriptor<ReqT, RespT> methodDescriptor, Metadata metadata, Context.CancellableContext cancellableContext, DecompressorRegistry decompressorRegistry, CompressorRegistry compressorRegistry, CallTracer callTracer, Tag tag) {
        this.stream = serverStream;
        this.method = methodDescriptor;
        this.context = cancellableContext;
        this.messageAcceptEncoding = (byte[]) metadata.get(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        this.decompressorRegistry = decompressorRegistry;
        this.compressorRegistry = compressorRegistry;
        this.serverCallTracer = callTracer;
        callTracer.reportCallStarted();
        this.tag = tag;
    }

    @Override // io.grpc.ServerCall
    public void request(int i) {
        TaskCloseable taskCloseableTraceTask = PerfMark.traceTask("ServerCall.request");
        try {
            PerfMark.attachTag(this.tag);
            this.stream.request(i);
            if (taskCloseableTraceTask != null) {
                taskCloseableTraceTask.close();
            }
        } catch (Throwable th) {
            if (taskCloseableTraceTask != null) {
                try {
                    taskCloseableTraceTask.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    @Override // io.grpc.ServerCall
    public void sendHeaders(Metadata metadata) {
        TaskCloseable taskCloseableTraceTask = PerfMark.traceTask("ServerCall.sendHeaders");
        try {
            PerfMark.attachTag(this.tag);
            sendHeadersInternal(metadata);
            if (taskCloseableTraceTask != null) {
                taskCloseableTraceTask.close();
            }
        } catch (Throwable th) {
            if (taskCloseableTraceTask != null) {
                try {
                    taskCloseableTraceTask.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void sendHeadersInternal(Metadata metadata) {
        Preconditions.checkState(!this.sendHeadersCalled, "sendHeaders has already been called");
        Preconditions.checkState(!this.closeCalled, "call is closed");
        metadata.discardAll(GrpcUtil.CONTENT_LENGTH_KEY);
        metadata.discardAll(GrpcUtil.MESSAGE_ENCODING_KEY);
        if (this.compressor == null || this.messageAcceptEncoding == null || !GrpcUtil.iterableContains(GrpcUtil.ACCEPT_ENCODING_SPLITTER.split(new String(this.messageAcceptEncoding, GrpcUtil.US_ASCII)), this.compressor.getMessageEncoding())) {
            this.compressor = Codec.Identity.NONE;
        }
        metadata.put(GrpcUtil.MESSAGE_ENCODING_KEY, this.compressor.getMessageEncoding());
        this.stream.setCompressor(this.compressor);
        metadata.discardAll(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY);
        byte[] rawAdvertisedMessageEncodings = InternalDecompressorRegistry.getRawAdvertisedMessageEncodings(this.decompressorRegistry);
        if (rawAdvertisedMessageEncodings.length != 0) {
            metadata.put(GrpcUtil.MESSAGE_ACCEPT_ENCODING_KEY, rawAdvertisedMessageEncodings);
        }
        this.sendHeadersCalled = true;
        this.stream.writeHeaders(metadata, true ^ getMethodDescriptor().getType().serverSendsOneMessage());
    }

    @Override // io.grpc.ServerCall
    public void sendMessage(RespT respt) {
        TaskCloseable taskCloseableTraceTask = PerfMark.traceTask("ServerCall.sendMessage");
        try {
            PerfMark.attachTag(this.tag);
            sendMessageInternal(respt);
            if (taskCloseableTraceTask != null) {
                taskCloseableTraceTask.close();
            }
        } catch (Throwable th) {
            if (taskCloseableTraceTask != null) {
                try {
                    taskCloseableTraceTask.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void sendMessageInternal(RespT respt) {
        Preconditions.checkState(this.sendHeadersCalled, "sendHeaders has not been called");
        Preconditions.checkState(!this.closeCalled, "call is closed");
        if (this.method.getType().serverSendsOneMessage() && this.messageSent) {
            handleInternalError(Status.INTERNAL.withDescription(TOO_MANY_RESPONSES).asRuntimeException());
            return;
        }
        this.messageSent = true;
        try {
            this.stream.writeMessage(this.method.streamResponse(respt));
            if (getMethodDescriptor().getType().serverSendsOneMessage()) {
                return;
            }
            this.stream.flush();
        } catch (Error e) {
            close(Status.CANCELLED.withDescription("Server sendMessage() failed with Error"), new Metadata());
            throw e;
        } catch (RuntimeException e2) {
            handleInternalError(e2);
        }
    }

    @Override // io.grpc.ServerCall
    public void setMessageCompression(boolean z) {
        this.stream.setMessageCompression(z);
    }

    @Override // io.grpc.ServerCall
    public void setCompression(String str) {
        Preconditions.checkState(!this.sendHeadersCalled, "sendHeaders has been called");
        Compressor compressorLookupCompressor = this.compressorRegistry.lookupCompressor(str);
        this.compressor = compressorLookupCompressor;
        Preconditions.checkArgument(compressorLookupCompressor != null, "Unable to find compressor by name %s", str);
    }

    @Override // io.grpc.ServerCall
    public boolean isReady() {
        if (this.closeCalled) {
            return false;
        }
        return this.stream.isReady();
    }

    @Override // io.grpc.ServerCall
    public void close(Status status, Metadata metadata) {
        TaskCloseable taskCloseableTraceTask = PerfMark.traceTask("ServerCall.close");
        try {
            PerfMark.attachTag(this.tag);
            closeInternal(status, metadata);
            if (taskCloseableTraceTask != null) {
                taskCloseableTraceTask.close();
            }
        } catch (Throwable th) {
            if (taskCloseableTraceTask != null) {
                try {
                    taskCloseableTraceTask.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private void closeInternal(Status status, Metadata metadata) {
        Preconditions.checkState(!this.closeCalled, "call already closed");
        try {
            this.closeCalled = true;
            if (status.isOk() && this.method.getType().serverSendsOneMessage() && !this.messageSent) {
                handleInternalError(Status.INTERNAL.withDescription(MISSING_RESPONSE).asRuntimeException());
            } else {
                this.stream.close(status, metadata);
            }
        } finally {
            this.serverCallTracer.reportCallEnded(status.isOk());
        }
    }

    @Override // io.grpc.ServerCall
    public boolean isCancelled() {
        return this.cancelled;
    }

    ServerStreamListener newServerStreamListener(ServerCall.Listener<ReqT> listener) {
        return new ServerStreamListenerImpl(this, listener, this.context);
    }

    @Override // io.grpc.ServerCall
    public Attributes getAttributes() {
        return this.stream.getAttributes();
    }

    @Override // io.grpc.ServerCall
    public String getAuthority() {
        return this.stream.getAuthority();
    }

    @Override // io.grpc.ServerCall
    public MethodDescriptor<ReqT, RespT> getMethodDescriptor() {
        return this.method;
    }

    @Override // io.grpc.ServerCall
    public SecurityLevel getSecurityLevel() {
        Attributes attributes = getAttributes();
        if (attributes == null) {
            return super.getSecurityLevel();
        }
        SecurityLevel securityLevel = (SecurityLevel) attributes.get(GrpcAttributes.ATTR_SECURITY_LEVEL);
        return securityLevel == null ? super.getSecurityLevel() : securityLevel;
    }

    private void handleInternalError(Throwable th) {
        Status statusWithDescription;
        log.log(Level.WARNING, "Cancelling the stream because of internal error", th);
        if (th instanceof StatusRuntimeException) {
            statusWithDescription = ((StatusRuntimeException) th).getStatus();
        } else {
            statusWithDescription = Status.INTERNAL.withCause(th).withDescription("Internal error so cancelling stream.");
        }
        this.stream.cancel(statusWithDescription);
        this.serverCallTracer.reportCallEnded(false);
    }

    static final class ServerStreamListenerImpl<ReqT> implements ServerStreamListener {
        private final ServerCallImpl<ReqT, ?> call;
        private final Context.CancellableContext context;
        private final ServerCall.Listener<ReqT> listener;

        public ServerStreamListenerImpl(ServerCallImpl<ReqT, ?> serverCallImpl, ServerCall.Listener<ReqT> listener, Context.CancellableContext cancellableContext) {
            this.call = (ServerCallImpl) Preconditions.checkNotNull(serverCallImpl, NotificationCompat.CATEGORY_CALL);
            this.listener = (ServerCall.Listener) Preconditions.checkNotNull(listener, "listener must not be null");
            Context.CancellableContext cancellableContext2 = (Context.CancellableContext) Preconditions.checkNotNull(cancellableContext, "context");
            this.context = cancellableContext2;
            cancellableContext2.addListener(new Context.CancellationListener() { // from class: io.grpc.internal.ServerCallImpl.ServerStreamListenerImpl.1
                @Override // io.grpc.Context.CancellationListener
                public void cancelled(Context context) {
                    if (context.cancellationCause() != null) {
                        ServerStreamListenerImpl.this.call.cancelled = true;
                    }
                }
            }, MoreExecutors.directExecutor());
        }

        @Override // io.grpc.internal.StreamListener
        public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
            TaskCloseable taskCloseableTraceTask = PerfMark.traceTask("ServerStreamListener.messagesAvailable");
            try {
                PerfMark.attachTag(((ServerCallImpl) this.call).tag);
                messagesAvailableInternal(messageProducer);
                if (taskCloseableTraceTask != null) {
                    taskCloseableTraceTask.close();
                }
            } catch (Throwable th) {
                if (taskCloseableTraceTask != null) {
                    try {
                        taskCloseableTraceTask.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        private void messagesAvailableInternal(StreamListener.MessageProducer messageProducer) {
            if (((ServerCallImpl) this.call).cancelled) {
                GrpcUtil.closeQuietly(messageProducer);
                return;
            }
            while (true) {
                try {
                    InputStream next = messageProducer.next();
                    if (next == null) {
                        return;
                    }
                    try {
                        this.listener.onMessage((ReqT) ((ServerCallImpl) this.call).method.parseRequest(next));
                        next.close();
                    } finally {
                    }
                } catch (Throwable th) {
                    GrpcUtil.closeQuietly(messageProducer);
                    Throwables.throwIfUnchecked(th);
                    throw new RuntimeException(th);
                }
            }
        }

        @Override // io.grpc.internal.ServerStreamListener
        public void halfClosed() {
            TaskCloseable taskCloseableTraceTask = PerfMark.traceTask("ServerStreamListener.halfClosed");
            try {
                PerfMark.attachTag(((ServerCallImpl) this.call).tag);
                if (((ServerCallImpl) this.call).cancelled) {
                    if (taskCloseableTraceTask != null) {
                        taskCloseableTraceTask.close();
                    }
                } else {
                    this.listener.onHalfClose();
                    if (taskCloseableTraceTask != null) {
                        taskCloseableTraceTask.close();
                    }
                }
            } catch (Throwable th) {
                if (taskCloseableTraceTask != null) {
                    try {
                        taskCloseableTraceTask.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        @Override // io.grpc.internal.ServerStreamListener
        public void closed(Status status) {
            TaskCloseable taskCloseableTraceTask = PerfMark.traceTask("ServerStreamListener.closed");
            try {
                PerfMark.attachTag(((ServerCallImpl) this.call).tag);
                closedInternal(status);
                if (taskCloseableTraceTask != null) {
                    taskCloseableTraceTask.close();
                }
            } catch (Throwable th) {
                if (taskCloseableTraceTask != null) {
                    try {
                        taskCloseableTraceTask.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }

        private void closedInternal(Status status) {
            StatusRuntimeException statusRuntimeExceptionAsRuntimeException = null;
            try {
                if (!status.isOk()) {
                    ((ServerCallImpl) this.call).cancelled = true;
                    this.listener.onCancel();
                    statusRuntimeExceptionAsRuntimeException = InternalStatus.asRuntimeException(Status.CANCELLED.withDescription("RPC cancelled"), null, false);
                } else {
                    this.listener.onComplete();
                }
            } finally {
                this.context.cancel(null);
            }
        }

        @Override // io.grpc.internal.StreamListener
        public void onReady() {
            TaskCloseable taskCloseableTraceTask = PerfMark.traceTask("ServerStreamListener.onReady");
            try {
                PerfMark.attachTag(((ServerCallImpl) this.call).tag);
                if (((ServerCallImpl) this.call).cancelled) {
                    if (taskCloseableTraceTask != null) {
                        taskCloseableTraceTask.close();
                    }
                } else {
                    this.listener.onReady();
                    if (taskCloseableTraceTask != null) {
                        taskCloseableTraceTask.close();
                    }
                }
            } catch (Throwable th) {
                if (taskCloseableTraceTask != null) {
                    try {
                        taskCloseableTraceTask.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        }
    }
}
