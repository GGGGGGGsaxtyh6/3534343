package com.google.firestore.v1;

import com.google.firestore.v1.StructuredPipeline;
import com.google.firestore.v1.TransactionOptions;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import com.google.protobuf.Timestamp;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes3.dex */
public final class ExecutePipelineRequest extends GeneratedMessageLite<ExecutePipelineRequest, Builder> implements ExecutePipelineRequestOrBuilder {
    public static final int DATABASE_FIELD_NUMBER = 1;
    private static final ExecutePipelineRequest DEFAULT_INSTANCE;
    public static final int NEW_TRANSACTION_FIELD_NUMBER = 6;
    private static volatile Parser<ExecutePipelineRequest> PARSER = null;
    public static final int READ_TIME_FIELD_NUMBER = 7;
    public static final int STRUCTURED_PIPELINE_FIELD_NUMBER = 2;
    public static final int TRANSACTION_FIELD_NUMBER = 5;
    private Object consistencySelector_;
    private Object pipelineType_;
    private int pipelineTypeCase_ = 0;
    private int consistencySelectorCase_ = 0;
    private String database_ = "";

    private ExecutePipelineRequest() {
    }

    public enum PipelineTypeCase {
        STRUCTURED_PIPELINE(2),
        PIPELINETYPE_NOT_SET(0);

        private final int value;

        PipelineTypeCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static PipelineTypeCase valueOf(int i) {
            return forNumber(i);
        }

        public static PipelineTypeCase forNumber(int i) {
            if (i == 0) {
                return PIPELINETYPE_NOT_SET;
            }
            if (i != 2) {
                return null;
            }
            return STRUCTURED_PIPELINE;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
    public PipelineTypeCase getPipelineTypeCase() {
        return PipelineTypeCase.forNumber(this.pipelineTypeCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPipelineType() {
        this.pipelineTypeCase_ = 0;
        this.pipelineType_ = null;
    }

    public enum ConsistencySelectorCase {
        TRANSACTION(5),
        NEW_TRANSACTION(6),
        READ_TIME(7),
        CONSISTENCYSELECTOR_NOT_SET(0);

        private final int value;

        ConsistencySelectorCase(int i) {
            this.value = i;
        }

        @Deprecated
        public static ConsistencySelectorCase valueOf(int i) {
            return forNumber(i);
        }

        public static ConsistencySelectorCase forNumber(int i) {
            if (i == 0) {
                return CONSISTENCYSELECTOR_NOT_SET;
            }
            if (i == 5) {
                return TRANSACTION;
            }
            if (i == 6) {
                return NEW_TRANSACTION;
            }
            if (i != 7) {
                return null;
            }
            return READ_TIME;
        }

        public int getNumber() {
            return this.value;
        }
    }

    @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
    public ConsistencySelectorCase getConsistencySelectorCase() {
        return ConsistencySelectorCase.forNumber(this.consistencySelectorCase_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearConsistencySelector() {
        this.consistencySelectorCase_ = 0;
        this.consistencySelector_ = null;
    }

    @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
    public String getDatabase() {
        return this.database_;
    }

    @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
    public ByteString getDatabaseBytes() {
        return ByteString.copyFromUtf8(this.database_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabase(String str) {
        str.getClass();
        this.database_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDatabase() {
        this.database_ = getDefaultInstance().getDatabase();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDatabaseBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.database_ = byteString.toStringUtf8();
    }

    @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
    public boolean hasStructuredPipeline() {
        return this.pipelineTypeCase_ == 2;
    }

    @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
    public StructuredPipeline getStructuredPipeline() {
        if (this.pipelineTypeCase_ == 2) {
            return (StructuredPipeline) this.pipelineType_;
        }
        return StructuredPipeline.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStructuredPipeline(StructuredPipeline structuredPipeline) {
        structuredPipeline.getClass();
        this.pipelineType_ = structuredPipeline;
        this.pipelineTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeStructuredPipeline(StructuredPipeline structuredPipeline) {
        structuredPipeline.getClass();
        if (this.pipelineTypeCase_ == 2 && this.pipelineType_ != StructuredPipeline.getDefaultInstance()) {
            this.pipelineType_ = StructuredPipeline.newBuilder((StructuredPipeline) this.pipelineType_).mergeFrom(structuredPipeline).buildPartial();
        } else {
            this.pipelineType_ = structuredPipeline;
        }
        this.pipelineTypeCase_ = 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStructuredPipeline() {
        if (this.pipelineTypeCase_ == 2) {
            this.pipelineTypeCase_ = 0;
            this.pipelineType_ = null;
        }
    }

    @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
    public boolean hasTransaction() {
        return this.consistencySelectorCase_ == 5;
    }

    @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
    public ByteString getTransaction() {
        if (this.consistencySelectorCase_ == 5) {
            return (ByteString) this.consistencySelector_;
        }
        return ByteString.EMPTY;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTransaction(ByteString byteString) {
        byteString.getClass();
        this.consistencySelectorCase_ = 5;
        this.consistencySelector_ = byteString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearTransaction() {
        if (this.consistencySelectorCase_ == 5) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
    public boolean hasNewTransaction() {
        return this.consistencySelectorCase_ == 6;
    }

    @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
    public TransactionOptions getNewTransaction() {
        if (this.consistencySelectorCase_ == 6) {
            return (TransactionOptions) this.consistencySelector_;
        }
        return TransactionOptions.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNewTransaction(TransactionOptions transactionOptions) {
        transactionOptions.getClass();
        this.consistencySelector_ = transactionOptions;
        this.consistencySelectorCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeNewTransaction(TransactionOptions transactionOptions) {
        transactionOptions.getClass();
        if (this.consistencySelectorCase_ == 6 && this.consistencySelector_ != TransactionOptions.getDefaultInstance()) {
            this.consistencySelector_ = TransactionOptions.newBuilder((TransactionOptions) this.consistencySelector_).mergeFrom(transactionOptions).buildPartial();
        } else {
            this.consistencySelector_ = transactionOptions;
        }
        this.consistencySelectorCase_ = 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearNewTransaction() {
        if (this.consistencySelectorCase_ == 6) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
    public boolean hasReadTime() {
        return this.consistencySelectorCase_ == 7;
    }

    @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
    public Timestamp getReadTime() {
        if (this.consistencySelectorCase_ == 7) {
            return (Timestamp) this.consistencySelector_;
        }
        return Timestamp.getDefaultInstance();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setReadTime(Timestamp timestamp) {
        timestamp.getClass();
        this.consistencySelector_ = timestamp;
        this.consistencySelectorCase_ = 7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergeReadTime(Timestamp timestamp) {
        timestamp.getClass();
        if (this.consistencySelectorCase_ == 7 && this.consistencySelector_ != Timestamp.getDefaultInstance()) {
            this.consistencySelector_ = Timestamp.newBuilder((Timestamp) this.consistencySelector_).mergeFrom(timestamp).buildPartial();
        } else {
            this.consistencySelector_ = timestamp;
        }
        this.consistencySelectorCase_ = 7;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearReadTime() {
        if (this.consistencySelectorCase_ == 7) {
            this.consistencySelectorCase_ = 0;
            this.consistencySelector_ = null;
        }
    }

    public static ExecutePipelineRequest parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ExecutePipelineRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ExecutePipelineRequest parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExecutePipelineRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ExecutePipelineRequest parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ExecutePipelineRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ExecutePipelineRequest parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExecutePipelineRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ExecutePipelineRequest parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ExecutePipelineRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ExecutePipelineRequest parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ExecutePipelineRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ExecutePipelineRequest parseFrom(InputStream inputStream) throws IOException {
        return (ExecutePipelineRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ExecutePipelineRequest parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExecutePipelineRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ExecutePipelineRequest parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ExecutePipelineRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ExecutePipelineRequest parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExecutePipelineRequest) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ExecutePipelineRequest parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ExecutePipelineRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ExecutePipelineRequest parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ExecutePipelineRequest) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ExecutePipelineRequest executePipelineRequest) {
        return DEFAULT_INSTANCE.createBuilder(executePipelineRequest);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ExecutePipelineRequest, Builder> implements ExecutePipelineRequestOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(ExecutePipelineRequest.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
        public PipelineTypeCase getPipelineTypeCase() {
            return ((ExecutePipelineRequest) this.instance).getPipelineTypeCase();
        }

        public Builder clearPipelineType() {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).clearPipelineType();
            return this;
        }

        @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
        public ConsistencySelectorCase getConsistencySelectorCase() {
            return ((ExecutePipelineRequest) this.instance).getConsistencySelectorCase();
        }

        public Builder clearConsistencySelector() {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).clearConsistencySelector();
            return this;
        }

        @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
        public String getDatabase() {
            return ((ExecutePipelineRequest) this.instance).getDatabase();
        }

        @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
        public ByteString getDatabaseBytes() {
            return ((ExecutePipelineRequest) this.instance).getDatabaseBytes();
        }

        public Builder setDatabase(String str) {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).setDatabase(str);
            return this;
        }

        public Builder clearDatabase() {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).clearDatabase();
            return this;
        }

        public Builder setDatabaseBytes(ByteString byteString) {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).setDatabaseBytes(byteString);
            return this;
        }

        @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
        public boolean hasStructuredPipeline() {
            return ((ExecutePipelineRequest) this.instance).hasStructuredPipeline();
        }

        @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
        public StructuredPipeline getStructuredPipeline() {
            return ((ExecutePipelineRequest) this.instance).getStructuredPipeline();
        }

        public Builder setStructuredPipeline(StructuredPipeline structuredPipeline) {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).setStructuredPipeline(structuredPipeline);
            return this;
        }

        public Builder setStructuredPipeline(StructuredPipeline.Builder builder) {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).setStructuredPipeline(builder.build());
            return this;
        }

        public Builder mergeStructuredPipeline(StructuredPipeline structuredPipeline) {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).mergeStructuredPipeline(structuredPipeline);
            return this;
        }

        public Builder clearStructuredPipeline() {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).clearStructuredPipeline();
            return this;
        }

        @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
        public boolean hasTransaction() {
            return ((ExecutePipelineRequest) this.instance).hasTransaction();
        }

        @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
        public ByteString getTransaction() {
            return ((ExecutePipelineRequest) this.instance).getTransaction();
        }

        public Builder setTransaction(ByteString byteString) {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).setTransaction(byteString);
            return this;
        }

        public Builder clearTransaction() {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).clearTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
        public boolean hasNewTransaction() {
            return ((ExecutePipelineRequest) this.instance).hasNewTransaction();
        }

        @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
        public TransactionOptions getNewTransaction() {
            return ((ExecutePipelineRequest) this.instance).getNewTransaction();
        }

        public Builder setNewTransaction(TransactionOptions transactionOptions) {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).setNewTransaction(transactionOptions);
            return this;
        }

        public Builder setNewTransaction(TransactionOptions.Builder builder) {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).setNewTransaction(builder.build());
            return this;
        }

        public Builder mergeNewTransaction(TransactionOptions transactionOptions) {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).mergeNewTransaction(transactionOptions);
            return this;
        }

        public Builder clearNewTransaction() {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).clearNewTransaction();
            return this;
        }

        @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
        public boolean hasReadTime() {
            return ((ExecutePipelineRequest) this.instance).hasReadTime();
        }

        @Override // com.google.firestore.v1.ExecutePipelineRequestOrBuilder
        public Timestamp getReadTime() {
            return ((ExecutePipelineRequest) this.instance).getReadTime();
        }

        public Builder setReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).setReadTime(timestamp);
            return this;
        }

        public Builder setReadTime(Timestamp.Builder builder) {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).setReadTime(builder.build());
            return this;
        }

        public Builder mergeReadTime(Timestamp timestamp) {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).mergeReadTime(timestamp);
            return this;
        }

        public Builder clearReadTime() {
            copyOnWrite();
            ((ExecutePipelineRequest) this.instance).clearReadTime();
            return this;
        }
    }

    /* JADX INFO: renamed from: com.google.firestore.v1.ExecutePipelineRequest$1, reason: invalid class name */
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
                return new ExecutePipelineRequest();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0005\u0002\u0000\u0001\u0007\u0005\u0000\u0000\u0000\u0001Ȉ\u0002<\u0000\u0005=\u0001\u0006<\u0001\u0007<\u0001", new Object[]{"pipelineType_", "pipelineTypeCase_", "consistencySelector_", "consistencySelectorCase_", "database_", StructuredPipeline.class, TransactionOptions.class, Timestamp.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ExecutePipelineRequest> parser = PARSER;
                if (parser != null) {
                    return parser;
                }
                synchronized (ExecutePipelineRequest.class) {
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
        ExecutePipelineRequest executePipelineRequest = new ExecutePipelineRequest();
        DEFAULT_INSTANCE = executePipelineRequest;
        GeneratedMessageLite.registerDefaultInstance(ExecutePipelineRequest.class, executePipelineRequest);
    }

    public static ExecutePipelineRequest getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ExecutePipelineRequest> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
