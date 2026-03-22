package com.google.firestore.v1;

import com.google.firestore.v1.Document;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public final class ExecutePipelineResponse extends GeneratedMessageLite<ExecutePipelineResponse, Builder> implements ExecutePipelineResponseOrBuilder {
    private static final ExecutePipelineResponse DEFAULT_INSTANCE;
    public static final int EXECUTION_TIME_FIELD_NUMBER = 3;
    private static volatile Parser<ExecutePipelineResponse> PARSER = null;
    public static final int RESULTS_FIELD_NUMBER = 2;
    public static final int TRANSACTION_FIELD_NUMBER = 1;
    private int bitField0_;
    private Timestamp executionTime_;
    private ByteString transaction_ = ByteString.EMPTY;
    private Internal.ProtobufList<Document> results_ = emptyProtobufList();

    private ExecutePipelineResponse() {
    }

    @Override // com.google.firestore.v1.ExecutePipelineResponseOrBuilder
    public ByteString getTransaction() {
        return this.transaction_;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransaction(ByteString byteString) {
        byteString.getClass();
        this.transaction_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        this.transaction_ = getDefaultInstance().getTransaction();
    }

    @Override // com.google.firestore.v1.ExecutePipelineResponseOrBuilder
    public List<Document> getResultsList() {
        return this.results_;
    }

    public List<? extends DocumentOrBuilder> getResultsOrBuilderList() {
        return this.results_;
    }

    @Override // com.google.firestore.v1.ExecutePipelineResponseOrBuilder
    public int getResultsCount() {
        return this.results_.size();
    }

    @Override // com.google.firestore.v1.ExecutePipelineResponseOrBuilder
    public Document getResults(int i) {
        return this.results_.get(i);
    }

    public DocumentOrBuilder getResultsOrBuilder(int i) {
        return this.results_.get(i);
    }

    private void ensureResultsIsMutable() {
        Internal.ProtobufList<Document> protobufList = this.results_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.results_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResults(int i, Document document) {
        document.getClass();
        ensureResultsIsMutable();
        this.results_.set(i, document);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addResults(Document document) {
        document.getClass();
        ensureResultsIsMutable();
        this.results_.add(document);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addResults(int i, Document document) {
        document.getClass();
        ensureResultsIsMutable();
        this.results_.add(i, document);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllResults(Iterable<? extends Document> iterable) {
        ensureResultsIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.results_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResults() {
        this.results_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeResults(int i) {
        ensureResultsIsMutable();
        this.results_.remove(i);
    }

    @Override // com.google.firestore.v1.ExecutePipelineResponseOrBuilder
    public boolean hasExecutionTime() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.google.firestore.v1.ExecutePipelineResponseOrBuilder
    public Timestamp getExecutionTime() {
        Timestamp timestamp = this.executionTime_;
        return timestamp == null ? Timestamp.getDefaultInstance() : timestamp;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setExecutionTime(Timestamp timestamp) {
        timestamp.getClass();
        this.executionTime_ = timestamp;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeExecutionTime(Timestamp timestamp) {
        timestamp.getClass();
        Timestamp timestamp2 = this.executionTime_;
        if (timestamp2 != null && timestamp2 != Timestamp.getDefaultInstance()) {
            this.executionTime_ = Timestamp.newBuilder(this.executionTime_).mergeFrom(timestamp).buildPartial();
        } else {
            this.executionTime_ = timestamp;
        }
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearExecutionTime() {
        this.executionTime_ = null;
        this.bitField0_ &= -2;
    }

    public static ExecutePipelineResponse parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ExecutePipelineResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ExecutePipelineResponse parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExecutePipelineResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ExecutePipelineResponse parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ExecutePipelineResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ExecutePipelineResponse parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExecutePipelineResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ExecutePipelineResponse parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ExecutePipelineResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ExecutePipelineResponse parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExecutePipelineResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ExecutePipelineResponse parseFrom(InputStream inputStream) throws IOException {
        return (ExecutePipelineResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ExecutePipelineResponse parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExecutePipelineResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ExecutePipelineResponse parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ExecutePipelineResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ExecutePipelineResponse parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExecutePipelineResponse) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ExecutePipelineResponse parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ExecutePipelineResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ExecutePipelineResponse parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExecutePipelineResponse) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ExecutePipelineResponse executePipelineResponse) {
        return DEFAULT_INSTANCE.createBuilder(executePipelineResponse);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ExecutePipelineResponse, Builder> implements ExecutePipelineResponseOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(ExecutePipelineResponse.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.ExecutePipelineResponseOrBuilder
        public ByteString getTransaction() {
            return ((ExecutePipelineResponse) this.instance).getTransaction();
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).setTransaction(byteString);
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.ExecutePipelineResponseOrBuilder
        public List<Document> getResultsList() {
            return Collections.unmodifiableList(((ExecutePipelineResponse) this.instance).getResultsList());
        }

        @Override // com.google.firestore.v1.ExecutePipelineResponseOrBuilder
        public int getResultsCount() {
            return ((ExecutePipelineResponse) this.instance).getResultsCount();
        }

        @Override // com.google.firestore.v1.ExecutePipelineResponseOrBuilder
        public Document getResults(int i) {
            return ((ExecutePipelineResponse) this.instance).getResults(i);
        }

        public Builder setResults(int i, Document document) {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).setResults(i, document);
            return this;
        }

        public Builder setResults(int i, Document.Builder builder) {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).setResults(i, builder.build());
            return this;
        }

        public Builder addResults(Document document) {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).addResults(document);
            return this;
        }

        public Builder addResults(int i, Document document) {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).addResults(i, document);
            return this;
        }

        public Builder addResults(Document.Builder builder) {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).addResults(builder.build());
            return this;
        }

        public Builder addResults(int i, Document.Builder builder) {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).addResults(i, builder.build());
            return this;
        }

        public Builder addAllResults(Iterable<? extends Document> iterable) {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).addAllResults(iterable);
            return this;
        }

        public Builder clearResults() {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).clearResults();
            return this;
        }

        public Builder removeResults(int i) {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).removeResults(i);
            return this;
        }

        @Override // com.google.firestore.v1.ExecutePipelineResponseOrBuilder
        public boolean hasExecutionTime() {
            return ((ExecutePipelineResponse) this.instance).hasExecutionTime();
        }

        @Override // com.google.firestore.v1.ExecutePipelineResponseOrBuilder
        public Timestamp getExecutionTime() {
            return ((ExecutePipelineResponse) this.instance).getExecutionTime();
        }

        public Builder setExecutionTime(Timestamp timestamp) {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).setExecutionTime(timestamp);
            return this;
        }

        public Builder setExecutionTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).setExecutionTime(builder.build());
            return this;
        }

        public Builder mergeExecutionTime(Timestamp timestamp) {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).mergeExecutionTime(timestamp);
            return this;
        }

        public Builder clearExecutionTime() {
            copyOnWrite();
            ((ExecutePipelineResponse) this.instance).clearExecutionTime();
            return this;
        }
    }

    /* JADX INFO: renamed from: com.google.firestore.v1.ExecutePipelineResponse$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        Parser defaultInstanceBasedParser;
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ExecutePipelineResponse();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\n\u0002\u001b\u0003ဉ\u0000", new Object[]{"bitField0_", "transaction_", "results_", Document.class, "executionTime_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ExecutePipelineResponse> parser = PARSER;
                if (parser != null) {
                    return parser;
                }
                synchronized (ExecutePipelineResponse.class) {
                    defaultInstanceBasedParser = PARSER;
                    if (defaultInstanceBasedParser == null) {
                        defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser(DEFAULT_INSTANCE);
                        PARSER = defaultInstanceBasedParser;
                    }
                    break;
                }
                return defaultInstanceBasedParser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        ExecutePipelineResponse executePipelineResponse = new ExecutePipelineResponse();
        DEFAULT_INSTANCE = executePipelineResponse;
        GeneratedMessageLite.registerDefaultInstance(ExecutePipelineResponse.class, executePipelineResponse);
    }

    public static ExecutePipelineResponse getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ExecutePipelineResponse> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
