package com.google.firestore.v1;

import com.google.firestore.v1.Pipeline;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class StructuredPipeline extends GeneratedMessageLite<StructuredPipeline, Builder> implements StructuredPipelineOrBuilder {
    private static final StructuredPipeline DEFAULT_INSTANCE;
    public static final int OPTIONS_FIELD_NUMBER = 2;
    private static volatile Parser<StructuredPipeline> PARSER = null;
    public static final int PIPELINE_FIELD_NUMBER = 1;
    private int bitField0_;
    private MapFieldLite<String, Value> options_ = MapFieldLite.emptyMapField();
    private Pipeline pipeline_;

    private StructuredPipeline() {
    }

    @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
    public boolean hasPipeline() {
        return (this.bitField0_ & 1) != 0;
    }

    @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
    public Pipeline getPipeline() {
        Pipeline pipeline = this.pipeline_;
        return pipeline == null ? Pipeline.getDefaultInstance() : pipeline;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setPipeline(Pipeline pipeline) {
        pipeline.getClass();
        this.pipeline_ = pipeline;
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mergePipeline(Pipeline pipeline) {
        pipeline.getClass();
        Pipeline pipeline2 = this.pipeline_;
        if (pipeline2 != null && pipeline2 != Pipeline.getDefaultInstance()) {
            this.pipeline_ = Pipeline.newBuilder(this.pipeline_).mergeFrom(pipeline).buildPartial();
        } else {
            this.pipeline_ = pipeline;
        }
        this.bitField0_ |= 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPipeline() {
        this.pipeline_ = null;
        this.bitField0_ &= -2;
    }

    private static final class OptionsDefaultEntryHolder {
        static final MapEntryLite<String, Value> defaultEntry = MapEntryLite.newDefaultInstance(WireFormat.FieldType.STRING, "", WireFormat.FieldType.MESSAGE, Value.getDefaultInstance());

        private OptionsDefaultEntryHolder() {
        }
    }

    private MapFieldLite<String, Value> internalGetOptions() {
        return this.options_;
    }

    private MapFieldLite<String, Value> internalGetMutableOptions() {
        if (!this.options_.isMutable()) {
            this.options_ = this.options_.mutableCopy();
        }
        return this.options_;
    }

    @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
    public int getOptionsCount() {
        return internalGetOptions().size();
    }

    @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
    public boolean containsOptions(String str) {
        str.getClass();
        return internalGetOptions().containsKey(str);
    }

    @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
    @Deprecated
    public Map<String, Value> getOptions() {
        return getOptionsMap();
    }

    @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
    public Map<String, Value> getOptionsMap() {
        return Collections.unmodifiableMap(internalGetOptions());
    }

    @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
    public Value getOptionsOrDefault(String str, Value value) {
        str.getClass();
        MapFieldLite<String, Value> mapFieldLiteInternalGetOptions = internalGetOptions();
        return mapFieldLiteInternalGetOptions.containsKey(str) ? mapFieldLiteInternalGetOptions.get(str) : value;
    }

    @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
    public Value getOptionsOrThrow(String str) {
        str.getClass();
        MapFieldLite<String, Value> mapFieldLiteInternalGetOptions = internalGetOptions();
        if (!mapFieldLiteInternalGetOptions.containsKey(str)) {
            throw new IllegalArgumentException();
        }
        return mapFieldLiteInternalGetOptions.get(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Map<String, Value> getMutableOptionsMap() {
        return internalGetMutableOptions();
    }

    public static StructuredPipeline parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (StructuredPipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static StructuredPipeline parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StructuredPipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static StructuredPipeline parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (StructuredPipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static StructuredPipeline parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StructuredPipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static StructuredPipeline parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (StructuredPipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static StructuredPipeline parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (StructuredPipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static StructuredPipeline parseFrom(InputStream inputStream) throws IOException {
        return (StructuredPipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static StructuredPipeline parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StructuredPipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static StructuredPipeline parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (StructuredPipeline) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static StructuredPipeline parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StructuredPipeline) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static StructuredPipeline parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (StructuredPipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static StructuredPipeline parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (StructuredPipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(StructuredPipeline structuredPipeline) {
        return DEFAULT_INSTANCE.createBuilder(structuredPipeline);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<StructuredPipeline, Builder> implements StructuredPipelineOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(StructuredPipeline.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
        public boolean hasPipeline() {
            return ((StructuredPipeline) this.instance).hasPipeline();
        }

        @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
        public Pipeline getPipeline() {
            return ((StructuredPipeline) this.instance).getPipeline();
        }

        public Builder setPipeline(Pipeline pipeline) {
            copyOnWrite();
            ((StructuredPipeline) this.instance).setPipeline(pipeline);
            return this;
        }

        public Builder setPipeline(Pipeline.Builder builder) {
            copyOnWrite();
            ((StructuredPipeline) this.instance).setPipeline(builder.build());
            return this;
        }

        public Builder mergePipeline(Pipeline pipeline) {
            copyOnWrite();
            ((StructuredPipeline) this.instance).mergePipeline(pipeline);
            return this;
        }

        public Builder clearPipeline() {
            copyOnWrite();
            ((StructuredPipeline) this.instance).clearPipeline();
            return this;
        }

        @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
        public int getOptionsCount() {
            return ((StructuredPipeline) this.instance).getOptionsMap().size();
        }

        @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
        public boolean containsOptions(String str) {
            str.getClass();
            return ((StructuredPipeline) this.instance).getOptionsMap().containsKey(str);
        }

        public Builder clearOptions() {
            copyOnWrite();
            ((StructuredPipeline) this.instance).getMutableOptionsMap().clear();
            return this;
        }

        public Builder removeOptions(String str) {
            str.getClass();
            copyOnWrite();
            ((StructuredPipeline) this.instance).getMutableOptionsMap().remove(str);
            return this;
        }

        @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
        @Deprecated
        public Map<String, Value> getOptions() {
            return getOptionsMap();
        }

        @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
        public Map<String, Value> getOptionsMap() {
            return Collections.unmodifiableMap(((StructuredPipeline) this.instance).getOptionsMap());
        }

        @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
        public Value getOptionsOrDefault(String str, Value value) {
            str.getClass();
            Map<String, Value> optionsMap = ((StructuredPipeline) this.instance).getOptionsMap();
            return optionsMap.containsKey(str) ? optionsMap.get(str) : value;
        }

        @Override // com.google.firestore.v1.StructuredPipelineOrBuilder
        public Value getOptionsOrThrow(String str) {
            str.getClass();
            Map<String, Value> optionsMap = ((StructuredPipeline) this.instance).getOptionsMap();
            if (!optionsMap.containsKey(str)) {
                throw new IllegalArgumentException();
            }
            return optionsMap.get(str);
        }

        public Builder putOptions(String str, Value value) {
            str.getClass();
            value.getClass();
            copyOnWrite();
            ((StructuredPipeline) this.instance).getMutableOptionsMap().put(str, value);
            return this;
        }

        public Builder putAllOptions(Map<String, Value> map) {
            copyOnWrite();
            ((StructuredPipeline) this.instance).getMutableOptionsMap().putAll(map);
            return this;
        }
    }

    /* JADX INFO: renamed from: com.google.firestore.v1.StructuredPipeline$1, reason: invalid class name */
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
                return new StructuredPipeline();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0001\u0001\u0002\u0002\u0001\u0000\u0000\u0001ဉ\u0000\u00022", new Object[]{"bitField0_", "pipeline_", "options_", OptionsDefaultEntryHolder.defaultEntry});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<StructuredPipeline> parser = PARSER;
                if (parser != null) {
                    return parser;
                }
                synchronized (StructuredPipeline.class) {
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
        StructuredPipeline structuredPipeline = new StructuredPipeline();
        DEFAULT_INSTANCE = structuredPipeline;
        GeneratedMessageLite.registerDefaultInstance(StructuredPipeline.class, structuredPipeline);
    }

    public static StructuredPipeline getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<StructuredPipeline> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
