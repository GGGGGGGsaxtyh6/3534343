package com.google.firestore.v1;

import com.google.common.util.concurrent.ListenableFuture;
import com.google.protobuf.Empty;
import io.grpc.BindableService;
import io.grpc.CallOptions;
import io.grpc.Channel;
import io.grpc.MethodDescriptor;
import io.grpc.ServerServiceDefinition;
import io.grpc.ServiceDescriptor;
import io.grpc.protobuf.lite.ProtoLiteUtils;
import io.grpc.stub.AbstractAsyncStub;
import io.grpc.stub.AbstractBlockingStub;
import io.grpc.stub.AbstractFutureStub;
import io.grpc.stub.AbstractStub;
import io.grpc.stub.ClientCalls;
import io.grpc.stub.ServerCalls;
import io.grpc.stub.StreamObserver;
import java.util.Iterator;

/* JADX INFO: loaded from: classes3.dex */
public final class FirestoreGrpc {
    private static final int METHODID_BATCH_GET_DOCUMENTS = 4;
    private static final int METHODID_BEGIN_TRANSACTION = 5;
    private static final int METHODID_COMMIT = 6;
    private static final int METHODID_CREATE_DOCUMENT = 12;
    private static final int METHODID_DELETE_DOCUMENT = 3;
    private static final int METHODID_EXECUTE_PIPELINE = 9;
    private static final int METHODID_GET_DOCUMENT = 0;
    private static final int METHODID_LISTEN = 14;
    private static final int METHODID_LIST_COLLECTION_IDS = 11;
    private static final int METHODID_LIST_DOCUMENTS = 1;
    private static final int METHODID_ROLLBACK = 7;
    private static final int METHODID_RUN_AGGREGATION_QUERY = 10;
    private static final int METHODID_RUN_QUERY = 8;
    private static final int METHODID_UPDATE_DOCUMENT = 2;
    private static final int METHODID_WRITE = 13;
    public static final String SERVICE_NAME = "google.firestore.v1.Firestore";
    private static volatile MethodDescriptor<BatchGetDocumentsRequest, BatchGetDocumentsResponse> getBatchGetDocumentsMethod;
    private static volatile MethodDescriptor<BeginTransactionRequest, BeginTransactionResponse> getBeginTransactionMethod;
    private static volatile MethodDescriptor<CommitRequest, CommitResponse> getCommitMethod;
    private static volatile MethodDescriptor<CreateDocumentRequest, Document> getCreateDocumentMethod;
    private static volatile MethodDescriptor<DeleteDocumentRequest, Empty> getDeleteDocumentMethod;
    private static volatile MethodDescriptor<ExecutePipelineRequest, ExecutePipelineResponse> getExecutePipelineMethod;
    private static volatile MethodDescriptor<GetDocumentRequest, Document> getGetDocumentMethod;
    private static volatile MethodDescriptor<ListCollectionIdsRequest, ListCollectionIdsResponse> getListCollectionIdsMethod;
    private static volatile MethodDescriptor<ListDocumentsRequest, ListDocumentsResponse> getListDocumentsMethod;
    private static volatile MethodDescriptor<ListenRequest, ListenResponse> getListenMethod;
    private static volatile MethodDescriptor<RollbackRequest, Empty> getRollbackMethod;
    private static volatile MethodDescriptor<RunAggregationQueryRequest, RunAggregationQueryResponse> getRunAggregationQueryMethod;
    private static volatile MethodDescriptor<RunQueryRequest, RunQueryResponse> getRunQueryMethod;
    private static volatile MethodDescriptor<UpdateDocumentRequest, Document> getUpdateDocumentMethod;
    private static volatile MethodDescriptor<WriteRequest, WriteResponse> getWriteMethod;
    private static volatile ServiceDescriptor serviceDescriptor;

    private FirestoreGrpc() {
    }

    public static MethodDescriptor<GetDocumentRequest, Document> getGetDocumentMethod() {
        MethodDescriptor<GetDocumentRequest, Document> methodDescriptorBuild;
        MethodDescriptor<GetDocumentRequest, Document> methodDescriptor = getGetDocumentMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getGetDocumentMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "GetDocument")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(GetDocumentRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(Document.getDefaultInstance())).build();
                getGetDocumentMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<ListDocumentsRequest, ListDocumentsResponse> getListDocumentsMethod() {
        MethodDescriptor<ListDocumentsRequest, ListDocumentsResponse> methodDescriptorBuild;
        MethodDescriptor<ListDocumentsRequest, ListDocumentsResponse> methodDescriptor = getListDocumentsMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getListDocumentsMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ListDocuments")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(ListDocumentsRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(ListDocumentsResponse.getDefaultInstance())).build();
                getListDocumentsMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<UpdateDocumentRequest, Document> getUpdateDocumentMethod() {
        MethodDescriptor<UpdateDocumentRequest, Document> methodDescriptorBuild;
        MethodDescriptor<UpdateDocumentRequest, Document> methodDescriptor = getUpdateDocumentMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getUpdateDocumentMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "UpdateDocument")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(UpdateDocumentRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(Document.getDefaultInstance())).build();
                getUpdateDocumentMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<DeleteDocumentRequest, Empty> getDeleteDocumentMethod() {
        MethodDescriptor<DeleteDocumentRequest, Empty> methodDescriptorBuild;
        MethodDescriptor<DeleteDocumentRequest, Empty> methodDescriptor = getDeleteDocumentMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getDeleteDocumentMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "DeleteDocument")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(DeleteDocumentRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(Empty.getDefaultInstance())).build();
                getDeleteDocumentMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<BatchGetDocumentsRequest, BatchGetDocumentsResponse> getBatchGetDocumentsMethod() {
        MethodDescriptor<BatchGetDocumentsRequest, BatchGetDocumentsResponse> methodDescriptorBuild;
        MethodDescriptor<BatchGetDocumentsRequest, BatchGetDocumentsResponse> methodDescriptor = getBatchGetDocumentsMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getBatchGetDocumentsMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.SERVER_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "BatchGetDocuments")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(BatchGetDocumentsRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(BatchGetDocumentsResponse.getDefaultInstance())).build();
                getBatchGetDocumentsMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<BeginTransactionRequest, BeginTransactionResponse> getBeginTransactionMethod() {
        MethodDescriptor<BeginTransactionRequest, BeginTransactionResponse> methodDescriptorBuild;
        MethodDescriptor<BeginTransactionRequest, BeginTransactionResponse> methodDescriptor = getBeginTransactionMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getBeginTransactionMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "BeginTransaction")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(BeginTransactionRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(BeginTransactionResponse.getDefaultInstance())).build();
                getBeginTransactionMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<CommitRequest, CommitResponse> getCommitMethod() {
        MethodDescriptor<CommitRequest, CommitResponse> methodDescriptorBuild;
        MethodDescriptor<CommitRequest, CommitResponse> methodDescriptor = getCommitMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getCommitMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "Commit")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(CommitRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(CommitResponse.getDefaultInstance())).build();
                getCommitMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<RollbackRequest, Empty> getRollbackMethod() {
        MethodDescriptor<RollbackRequest, Empty> methodDescriptorBuild;
        MethodDescriptor<RollbackRequest, Empty> methodDescriptor = getRollbackMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getRollbackMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "Rollback")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(RollbackRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(Empty.getDefaultInstance())).build();
                getRollbackMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<RunQueryRequest, RunQueryResponse> getRunQueryMethod() {
        MethodDescriptor<RunQueryRequest, RunQueryResponse> methodDescriptorBuild;
        MethodDescriptor<RunQueryRequest, RunQueryResponse> methodDescriptor = getRunQueryMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getRunQueryMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.SERVER_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "RunQuery")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(RunQueryRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(RunQueryResponse.getDefaultInstance())).build();
                getRunQueryMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<ExecutePipelineRequest, ExecutePipelineResponse> getExecutePipelineMethod() {
        MethodDescriptor<ExecutePipelineRequest, ExecutePipelineResponse> methodDescriptorBuild;
        MethodDescriptor<ExecutePipelineRequest, ExecutePipelineResponse> methodDescriptor = getExecutePipelineMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getExecutePipelineMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.SERVER_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ExecutePipeline")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(ExecutePipelineRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(ExecutePipelineResponse.getDefaultInstance())).build();
                getExecutePipelineMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<RunAggregationQueryRequest, RunAggregationQueryResponse> getRunAggregationQueryMethod() {
        MethodDescriptor<RunAggregationQueryRequest, RunAggregationQueryResponse> methodDescriptorBuild;
        MethodDescriptor<RunAggregationQueryRequest, RunAggregationQueryResponse> methodDescriptor = getRunAggregationQueryMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getRunAggregationQueryMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.SERVER_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "RunAggregationQuery")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(RunAggregationQueryRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(RunAggregationQueryResponse.getDefaultInstance())).build();
                getRunAggregationQueryMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<WriteRequest, WriteResponse> getWriteMethod() {
        MethodDescriptor<WriteRequest, WriteResponse> methodDescriptorBuild;
        MethodDescriptor<WriteRequest, WriteResponse> methodDescriptor = getWriteMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getWriteMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "Write")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(WriteRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(WriteResponse.getDefaultInstance())).build();
                getWriteMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<ListenRequest, ListenResponse> getListenMethod() {
        MethodDescriptor<ListenRequest, ListenResponse> methodDescriptorBuild;
        MethodDescriptor<ListenRequest, ListenResponse> methodDescriptor = getListenMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getListenMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.BIDI_STREAMING).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "Listen")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(ListenRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(ListenResponse.getDefaultInstance())).build();
                getListenMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<ListCollectionIdsRequest, ListCollectionIdsResponse> getListCollectionIdsMethod() {
        MethodDescriptor<ListCollectionIdsRequest, ListCollectionIdsResponse> methodDescriptorBuild;
        MethodDescriptor<ListCollectionIdsRequest, ListCollectionIdsResponse> methodDescriptor = getListCollectionIdsMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getListCollectionIdsMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "ListCollectionIds")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(ListCollectionIdsRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(ListCollectionIdsResponse.getDefaultInstance())).build();
                getListCollectionIdsMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static MethodDescriptor<CreateDocumentRequest, Document> getCreateDocumentMethod() {
        MethodDescriptor<CreateDocumentRequest, Document> methodDescriptorBuild;
        MethodDescriptor<CreateDocumentRequest, Document> methodDescriptor = getCreateDocumentMethod;
        if (methodDescriptor != null) {
            return methodDescriptor;
        }
        synchronized (FirestoreGrpc.class) {
            methodDescriptorBuild = getCreateDocumentMethod;
            if (methodDescriptorBuild == null) {
                methodDescriptorBuild = MethodDescriptor.newBuilder().setType(MethodDescriptor.MethodType.UNARY).setFullMethodName(MethodDescriptor.generateFullMethodName(SERVICE_NAME, "CreateDocument")).setSampledToLocalTracing(true).setRequestMarshaller(ProtoLiteUtils.marshaller(CreateDocumentRequest.getDefaultInstance())).setResponseMarshaller(ProtoLiteUtils.marshaller(Document.getDefaultInstance())).build();
                getCreateDocumentMethod = methodDescriptorBuild;
            }
        }
        return methodDescriptorBuild;
    }

    public static FirestoreStub newStub(Channel channel) {
        return (FirestoreStub) FirestoreStub.newStub(new AbstractStub.StubFactory<FirestoreStub>() { // from class: com.google.firestore.v1.FirestoreGrpc.1
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public FirestoreStub newStub(Channel channel2, CallOptions callOptions) {
                return new FirestoreStub(channel2, callOptions);
            }
        }, channel);
    }

    public static FirestoreBlockingStub newBlockingStub(Channel channel) {
        return (FirestoreBlockingStub) FirestoreBlockingStub.newStub(new AbstractStub.StubFactory<FirestoreBlockingStub>() { // from class: com.google.firestore.v1.FirestoreGrpc.2
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public FirestoreBlockingStub newStub(Channel channel2, CallOptions callOptions) {
                return new FirestoreBlockingStub(channel2, callOptions);
            }
        }, channel);
    }

    public static FirestoreFutureStub newFutureStub(Channel channel) {
        return (FirestoreFutureStub) FirestoreFutureStub.newStub(new AbstractStub.StubFactory<FirestoreFutureStub>() { // from class: com.google.firestore.v1.FirestoreGrpc.3
            @Override // io.grpc.stub.AbstractStub.StubFactory
            public FirestoreFutureStub newStub(Channel channel2, CallOptions callOptions) {
                return new FirestoreFutureStub(channel2, callOptions);
            }
        }, channel);
    }

    public interface AsyncService {
        default void getDocument(GetDocumentRequest getDocumentRequest, StreamObserver<Document> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(FirestoreGrpc.getGetDocumentMethod(), streamObserver);
        }

        default void listDocuments(ListDocumentsRequest listDocumentsRequest, StreamObserver<ListDocumentsResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(FirestoreGrpc.getListDocumentsMethod(), streamObserver);
        }

        default void updateDocument(UpdateDocumentRequest updateDocumentRequest, StreamObserver<Document> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(FirestoreGrpc.getUpdateDocumentMethod(), streamObserver);
        }

        default void deleteDocument(DeleteDocumentRequest deleteDocumentRequest, StreamObserver<Empty> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(FirestoreGrpc.getDeleteDocumentMethod(), streamObserver);
        }

        default void batchGetDocuments(BatchGetDocumentsRequest batchGetDocumentsRequest, StreamObserver<BatchGetDocumentsResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(FirestoreGrpc.getBatchGetDocumentsMethod(), streamObserver);
        }

        default void beginTransaction(BeginTransactionRequest beginTransactionRequest, StreamObserver<BeginTransactionResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(FirestoreGrpc.getBeginTransactionMethod(), streamObserver);
        }

        default void commit(CommitRequest commitRequest, StreamObserver<CommitResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(FirestoreGrpc.getCommitMethod(), streamObserver);
        }

        default void rollback(RollbackRequest rollbackRequest, StreamObserver<Empty> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(FirestoreGrpc.getRollbackMethod(), streamObserver);
        }

        default void runQuery(RunQueryRequest runQueryRequest, StreamObserver<RunQueryResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(FirestoreGrpc.getRunQueryMethod(), streamObserver);
        }

        default void executePipeline(ExecutePipelineRequest executePipelineRequest, StreamObserver<ExecutePipelineResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(FirestoreGrpc.getExecutePipelineMethod(), streamObserver);
        }

        default void runAggregationQuery(RunAggregationQueryRequest runAggregationQueryRequest, StreamObserver<RunAggregationQueryResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(FirestoreGrpc.getRunAggregationQueryMethod(), streamObserver);
        }

        default StreamObserver<WriteRequest> write(StreamObserver<WriteResponse> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(FirestoreGrpc.getWriteMethod(), streamObserver);
        }

        default StreamObserver<ListenRequest> listen(StreamObserver<ListenResponse> streamObserver) {
            return ServerCalls.asyncUnimplementedStreamingCall(FirestoreGrpc.getListenMethod(), streamObserver);
        }

        default void listCollectionIds(ListCollectionIdsRequest listCollectionIdsRequest, StreamObserver<ListCollectionIdsResponse> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(FirestoreGrpc.getListCollectionIdsMethod(), streamObserver);
        }

        default void createDocument(CreateDocumentRequest createDocumentRequest, StreamObserver<Document> streamObserver) {
            ServerCalls.asyncUnimplementedUnaryCall(FirestoreGrpc.getCreateDocumentMethod(), streamObserver);
        }
    }

    public static abstract class FirestoreImplBase implements BindableService, AsyncService {
        @Override // io.grpc.BindableService
        public final ServerServiceDefinition bindService() {
            return FirestoreGrpc.bindService(this);
        }
    }

    public static final class FirestoreStub extends AbstractAsyncStub<FirestoreStub> {
        private FirestoreStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public FirestoreStub build(Channel channel, CallOptions callOptions) {
            return new FirestoreStub(channel, callOptions);
        }

        public void getDocument(GetDocumentRequest getDocumentRequest, StreamObserver<Document> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(FirestoreGrpc.getGetDocumentMethod(), getCallOptions()), getDocumentRequest, streamObserver);
        }

        public void listDocuments(ListDocumentsRequest listDocumentsRequest, StreamObserver<ListDocumentsResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(FirestoreGrpc.getListDocumentsMethod(), getCallOptions()), listDocumentsRequest, streamObserver);
        }

        public void updateDocument(UpdateDocumentRequest updateDocumentRequest, StreamObserver<Document> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(FirestoreGrpc.getUpdateDocumentMethod(), getCallOptions()), updateDocumentRequest, streamObserver);
        }

        public void deleteDocument(DeleteDocumentRequest deleteDocumentRequest, StreamObserver<Empty> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(FirestoreGrpc.getDeleteDocumentMethod(), getCallOptions()), deleteDocumentRequest, streamObserver);
        }

        public void batchGetDocuments(BatchGetDocumentsRequest batchGetDocumentsRequest, StreamObserver<BatchGetDocumentsResponse> streamObserver) {
            ClientCalls.asyncServerStreamingCall(getChannel().newCall(FirestoreGrpc.getBatchGetDocumentsMethod(), getCallOptions()), batchGetDocumentsRequest, streamObserver);
        }

        public void beginTransaction(BeginTransactionRequest beginTransactionRequest, StreamObserver<BeginTransactionResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(FirestoreGrpc.getBeginTransactionMethod(), getCallOptions()), beginTransactionRequest, streamObserver);
        }

        public void commit(CommitRequest commitRequest, StreamObserver<CommitResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(FirestoreGrpc.getCommitMethod(), getCallOptions()), commitRequest, streamObserver);
        }

        public void rollback(RollbackRequest rollbackRequest, StreamObserver<Empty> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(FirestoreGrpc.getRollbackMethod(), getCallOptions()), rollbackRequest, streamObserver);
        }

        public void runQuery(RunQueryRequest runQueryRequest, StreamObserver<RunQueryResponse> streamObserver) {
            ClientCalls.asyncServerStreamingCall(getChannel().newCall(FirestoreGrpc.getRunQueryMethod(), getCallOptions()), runQueryRequest, streamObserver);
        }

        public void executePipeline(ExecutePipelineRequest executePipelineRequest, StreamObserver<ExecutePipelineResponse> streamObserver) {
            ClientCalls.asyncServerStreamingCall(getChannel().newCall(FirestoreGrpc.getExecutePipelineMethod(), getCallOptions()), executePipelineRequest, streamObserver);
        }

        public void runAggregationQuery(RunAggregationQueryRequest runAggregationQueryRequest, StreamObserver<RunAggregationQueryResponse> streamObserver) {
            ClientCalls.asyncServerStreamingCall(getChannel().newCall(FirestoreGrpc.getRunAggregationQueryMethod(), getCallOptions()), runAggregationQueryRequest, streamObserver);
        }

        public StreamObserver<WriteRequest> write(StreamObserver<WriteResponse> streamObserver) {
            return ClientCalls.asyncBidiStreamingCall(getChannel().newCall(FirestoreGrpc.getWriteMethod(), getCallOptions()), streamObserver);
        }

        public StreamObserver<ListenRequest> listen(StreamObserver<ListenResponse> streamObserver) {
            return ClientCalls.asyncBidiStreamingCall(getChannel().newCall(FirestoreGrpc.getListenMethod(), getCallOptions()), streamObserver);
        }

        public void listCollectionIds(ListCollectionIdsRequest listCollectionIdsRequest, StreamObserver<ListCollectionIdsResponse> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(FirestoreGrpc.getListCollectionIdsMethod(), getCallOptions()), listCollectionIdsRequest, streamObserver);
        }

        public void createDocument(CreateDocumentRequest createDocumentRequest, StreamObserver<Document> streamObserver) {
            ClientCalls.asyncUnaryCall(getChannel().newCall(FirestoreGrpc.getCreateDocumentMethod(), getCallOptions()), createDocumentRequest, streamObserver);
        }
    }

    public static final class FirestoreBlockingStub extends AbstractBlockingStub<FirestoreBlockingStub> {
        private FirestoreBlockingStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public FirestoreBlockingStub build(Channel channel, CallOptions callOptions) {
            return new FirestoreBlockingStub(channel, callOptions);
        }

        public Document getDocument(GetDocumentRequest getDocumentRequest) {
            return (Document) ClientCalls.blockingUnaryCall(getChannel(), FirestoreGrpc.getGetDocumentMethod(), getCallOptions(), getDocumentRequest);
        }

        public ListDocumentsResponse listDocuments(ListDocumentsRequest listDocumentsRequest) {
            return (ListDocumentsResponse) ClientCalls.blockingUnaryCall(getChannel(), FirestoreGrpc.getListDocumentsMethod(), getCallOptions(), listDocumentsRequest);
        }

        public Document updateDocument(UpdateDocumentRequest updateDocumentRequest) {
            return (Document) ClientCalls.blockingUnaryCall(getChannel(), FirestoreGrpc.getUpdateDocumentMethod(), getCallOptions(), updateDocumentRequest);
        }

        public Empty deleteDocument(DeleteDocumentRequest deleteDocumentRequest) {
            return (Empty) ClientCalls.blockingUnaryCall(getChannel(), FirestoreGrpc.getDeleteDocumentMethod(), getCallOptions(), deleteDocumentRequest);
        }

        public Iterator<BatchGetDocumentsResponse> batchGetDocuments(BatchGetDocumentsRequest batchGetDocumentsRequest) {
            return ClientCalls.blockingServerStreamingCall(getChannel(), FirestoreGrpc.getBatchGetDocumentsMethod(), getCallOptions(), batchGetDocumentsRequest);
        }

        public BeginTransactionResponse beginTransaction(BeginTransactionRequest beginTransactionRequest) {
            return (BeginTransactionResponse) ClientCalls.blockingUnaryCall(getChannel(), FirestoreGrpc.getBeginTransactionMethod(), getCallOptions(), beginTransactionRequest);
        }

        public CommitResponse commit(CommitRequest commitRequest) {
            return (CommitResponse) ClientCalls.blockingUnaryCall(getChannel(), FirestoreGrpc.getCommitMethod(), getCallOptions(), commitRequest);
        }

        public Empty rollback(RollbackRequest rollbackRequest) {
            return (Empty) ClientCalls.blockingUnaryCall(getChannel(), FirestoreGrpc.getRollbackMethod(), getCallOptions(), rollbackRequest);
        }

        public Iterator<RunQueryResponse> runQuery(RunQueryRequest runQueryRequest) {
            return ClientCalls.blockingServerStreamingCall(getChannel(), FirestoreGrpc.getRunQueryMethod(), getCallOptions(), runQueryRequest);
        }

        public Iterator<ExecutePipelineResponse> executePipeline(ExecutePipelineRequest executePipelineRequest) {
            return ClientCalls.blockingServerStreamingCall(getChannel(), FirestoreGrpc.getExecutePipelineMethod(), getCallOptions(), executePipelineRequest);
        }

        public Iterator<RunAggregationQueryResponse> runAggregationQuery(RunAggregationQueryRequest runAggregationQueryRequest) {
            return ClientCalls.blockingServerStreamingCall(getChannel(), FirestoreGrpc.getRunAggregationQueryMethod(), getCallOptions(), runAggregationQueryRequest);
        }

        public ListCollectionIdsResponse listCollectionIds(ListCollectionIdsRequest listCollectionIdsRequest) {
            return (ListCollectionIdsResponse) ClientCalls.blockingUnaryCall(getChannel(), FirestoreGrpc.getListCollectionIdsMethod(), getCallOptions(), listCollectionIdsRequest);
        }

        public Document createDocument(CreateDocumentRequest createDocumentRequest) {
            return (Document) ClientCalls.blockingUnaryCall(getChannel(), FirestoreGrpc.getCreateDocumentMethod(), getCallOptions(), createDocumentRequest);
        }
    }

    public static final class FirestoreFutureStub extends AbstractFutureStub<FirestoreFutureStub> {
        private FirestoreFutureStub(Channel channel, CallOptions callOptions) {
            super(channel, callOptions);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.stub.AbstractStub
        public FirestoreFutureStub build(Channel channel, CallOptions callOptions) {
            return new FirestoreFutureStub(channel, callOptions);
        }

        public ListenableFuture<Document> getDocument(GetDocumentRequest getDocumentRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(FirestoreGrpc.getGetDocumentMethod(), getCallOptions()), getDocumentRequest);
        }

        public ListenableFuture<ListDocumentsResponse> listDocuments(ListDocumentsRequest listDocumentsRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(FirestoreGrpc.getListDocumentsMethod(), getCallOptions()), listDocumentsRequest);
        }

        public ListenableFuture<Document> updateDocument(UpdateDocumentRequest updateDocumentRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(FirestoreGrpc.getUpdateDocumentMethod(), getCallOptions()), updateDocumentRequest);
        }

        public ListenableFuture<Empty> deleteDocument(DeleteDocumentRequest deleteDocumentRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(FirestoreGrpc.getDeleteDocumentMethod(), getCallOptions()), deleteDocumentRequest);
        }

        public ListenableFuture<BeginTransactionResponse> beginTransaction(BeginTransactionRequest beginTransactionRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(FirestoreGrpc.getBeginTransactionMethod(), getCallOptions()), beginTransactionRequest);
        }

        public ListenableFuture<CommitResponse> commit(CommitRequest commitRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(FirestoreGrpc.getCommitMethod(), getCallOptions()), commitRequest);
        }

        public ListenableFuture<Empty> rollback(RollbackRequest rollbackRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(FirestoreGrpc.getRollbackMethod(), getCallOptions()), rollbackRequest);
        }

        public ListenableFuture<ListCollectionIdsResponse> listCollectionIds(ListCollectionIdsRequest listCollectionIdsRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(FirestoreGrpc.getListCollectionIdsMethod(), getCallOptions()), listCollectionIdsRequest);
        }

        public ListenableFuture<Document> createDocument(CreateDocumentRequest createDocumentRequest) {
            return ClientCalls.futureUnaryCall(getChannel().newCall(FirestoreGrpc.getCreateDocumentMethod(), getCallOptions()), createDocumentRequest);
        }
    }

    private static final class MethodHandlers<Req, Resp> implements ServerCalls.UnaryMethod<Req, Resp>, ServerCalls.ServerStreamingMethod<Req, Resp>, ServerCalls.ClientStreamingMethod<Req, Resp>, ServerCalls.BidiStreamingMethod<Req, Resp> {
        private final int methodId;
        private final AsyncService serviceImpl;

        MethodHandlers(AsyncService asyncService, int i) {
            this.serviceImpl = asyncService;
            this.methodId = i;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.grpc.stub.ServerCalls.UnaryMethod, io.grpc.stub.ServerCalls.UnaryRequestMethod, io.grpc.stub.ServerCalls.ServerStreamingMethod
        public void invoke(Req req, StreamObserver<Resp> streamObserver) {
            switch (this.methodId) {
                case 0:
                    this.serviceImpl.getDocument((GetDocumentRequest) req, streamObserver);
                    return;
                case 1:
                    this.serviceImpl.listDocuments((ListDocumentsRequest) req, streamObserver);
                    return;
                case 2:
                    this.serviceImpl.updateDocument((UpdateDocumentRequest) req, streamObserver);
                    return;
                case 3:
                    this.serviceImpl.deleteDocument((DeleteDocumentRequest) req, streamObserver);
                    return;
                case 4:
                    this.serviceImpl.batchGetDocuments((BatchGetDocumentsRequest) req, streamObserver);
                    return;
                case 5:
                    this.serviceImpl.beginTransaction((BeginTransactionRequest) req, streamObserver);
                    return;
                case 6:
                    this.serviceImpl.commit((CommitRequest) req, streamObserver);
                    return;
                case 7:
                    this.serviceImpl.rollback((RollbackRequest) req, streamObserver);
                    return;
                case 8:
                    this.serviceImpl.runQuery((RunQueryRequest) req, streamObserver);
                    return;
                case 9:
                    this.serviceImpl.executePipeline((ExecutePipelineRequest) req, streamObserver);
                    return;
                case 10:
                    this.serviceImpl.runAggregationQuery((RunAggregationQueryRequest) req, streamObserver);
                    return;
                case 11:
                    this.serviceImpl.listCollectionIds((ListCollectionIdsRequest) req, streamObserver);
                    return;
                case 12:
                    this.serviceImpl.createDocument((CreateDocumentRequest) req, streamObserver);
                    return;
                default:
                    throw new AssertionError();
            }
        }

        @Override // io.grpc.stub.ServerCalls.ClientStreamingMethod, io.grpc.stub.ServerCalls.StreamingRequestMethod, io.grpc.stub.ServerCalls.BidiStreamingMethod
        public StreamObserver<Req> invoke(StreamObserver<Resp> streamObserver) {
            int i = this.methodId;
            if (i == 13) {
                return (StreamObserver<Req>) this.serviceImpl.write(streamObserver);
            }
            if (i == 14) {
                return (StreamObserver<Req>) this.serviceImpl.listen(streamObserver);
            }
            throw new AssertionError();
        }
    }

    public static final ServerServiceDefinition bindService(AsyncService asyncService) {
        return ServerServiceDefinition.builder(getServiceDescriptor()).addMethod(getGetDocumentMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(asyncService, 0))).addMethod(getListDocumentsMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(asyncService, 1))).addMethod(getUpdateDocumentMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(asyncService, 2))).addMethod(getDeleteDocumentMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(asyncService, 3))).addMethod(getBatchGetDocumentsMethod(), ServerCalls.asyncServerStreamingCall(new MethodHandlers(asyncService, 4))).addMethod(getBeginTransactionMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(asyncService, 5))).addMethod(getCommitMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(asyncService, 6))).addMethod(getRollbackMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(asyncService, 7))).addMethod(getRunQueryMethod(), ServerCalls.asyncServerStreamingCall(new MethodHandlers(asyncService, 8))).addMethod(getExecutePipelineMethod(), ServerCalls.asyncServerStreamingCall(new MethodHandlers(asyncService, 9))).addMethod(getRunAggregationQueryMethod(), ServerCalls.asyncServerStreamingCall(new MethodHandlers(asyncService, 10))).addMethod(getWriteMethod(), ServerCalls.asyncBidiStreamingCall(new MethodHandlers(asyncService, 13))).addMethod(getListenMethod(), ServerCalls.asyncBidiStreamingCall(new MethodHandlers(asyncService, 14))).addMethod(getListCollectionIdsMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(asyncService, 11))).addMethod(getCreateDocumentMethod(), ServerCalls.asyncUnaryCall(new MethodHandlers(asyncService, 12))).build();
    }

    public static ServiceDescriptor getServiceDescriptor() {
        ServiceDescriptor serviceDescriptorBuild;
        ServiceDescriptor serviceDescriptor2 = serviceDescriptor;
        if (serviceDescriptor2 != null) {
            return serviceDescriptor2;
        }
        synchronized (FirestoreGrpc.class) {
            serviceDescriptorBuild = serviceDescriptor;
            if (serviceDescriptorBuild == null) {
                serviceDescriptorBuild = ServiceDescriptor.newBuilder(SERVICE_NAME).addMethod(getGetDocumentMethod()).addMethod(getListDocumentsMethod()).addMethod(getUpdateDocumentMethod()).addMethod(getDeleteDocumentMethod()).addMethod(getBatchGetDocumentsMethod()).addMethod(getBeginTransactionMethod()).addMethod(getCommitMethod()).addMethod(getRollbackMethod()).addMethod(getRunQueryMethod()).addMethod(getExecutePipelineMethod()).addMethod(getRunAggregationQueryMethod()).addMethod(getWriteMethod()).addMethod(getListenMethod()).addMethod(getListCollectionIdsMethod()).addMethod(getCreateDocumentMethod()).build();
                serviceDescriptor = serviceDescriptorBuild;
            }
        }
        return serviceDescriptorBuild;
    }
}
