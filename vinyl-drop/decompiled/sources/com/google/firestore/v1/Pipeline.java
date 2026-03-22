package com.google.firestore.v1;

import com.google.firestore.v1.Value;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MapEntryLite;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.WireFormat;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public final class Pipeline extends GeneratedMessageLite<Pipeline, Builder> implements PipelineOrBuilder {
    private static final Pipeline DEFAULT_INSTANCE;
    private static volatile Parser<Pipeline> PARSER = null;
    public static final int STAGES_FIELD_NUMBER = 1;
    private Internal.ProtobufList<Stage> stages_ = emptyProtobufList();

    public interface StageOrBuilder extends MessageLiteOrBuilder {
        boolean containsOptions(String str);

        Value getArgs(int i);

        int getArgsCount();

        List<Value> getArgsList();

        String getName();

        ByteString getNameBytes();

        @Deprecated
        Map<String, Value> getOptions();

        int getOptionsCount();

        Map<String, Value> getOptionsMap();

        Value getOptionsOrDefault(String str, Value value);

        Value getOptionsOrThrow(String str);
    }

    private Pipeline() {
    }

    public static final class Stage extends GeneratedMessageLite<Stage, Builder> implements StageOrBuilder {
        public static final int ARGS_FIELD_NUMBER = 2;
        private static final Stage DEFAULT_INSTANCE;
        public static final int NAME_FIELD_NUMBER = 1;
        public static final int OPTIONS_FIELD_NUMBER = 3;
        private static volatile Parser<Stage> PARSER;
        private MapFieldLite<String, Value> options_ = MapFieldLite.emptyMapField();
        private String name_ = "";
        private Internal.ProtobufList<Value> args_ = emptyProtobufList();

        private Stage() {
        }

        @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
        public String getName() {
            return this.name_;
        }

        @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
        public ByteString getNameBytes() {
            return ByteString.copyFromUtf8(this.name_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setName(String str) {
            str.getClass();
            this.name_ = str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearName() {
            this.name_ = getDefaultInstance().getName();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setNameBytes(ByteString byteString) {
            checkByteStringIsUtf8(byteString);
            this.name_ = byteString.toStringUtf8();
        }

        @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
        public List<Value> getArgsList() {
            return this.args_;
        }

        public List<? extends ValueOrBuilder> getArgsOrBuilderList() {
            return this.args_;
        }

        @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
        public int getArgsCount() {
            return this.args_.size();
        }

        @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
        public Value getArgs(int i) {
            return this.args_.get(i);
        }

        public ValueOrBuilder getArgsOrBuilder(int i) {
            return this.args_.get(i);
        }

        private void ensureArgsIsMutable() {
            Internal.ProtobufList<Value> protobufList = this.args_;
            if (protobufList.isModifiable()) {
                return;
            }
            this.args_ = GeneratedMessageLite.mutableCopy(protobufList);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setArgs(int i, Value value) {
            value.getClass();
            ensureArgsIsMutable();
            this.args_.set(i, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addArgs(Value value) {
            value.getClass();
            ensureArgsIsMutable();
            this.args_.add(value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addArgs(int i, Value value) {
            value.getClass();
            ensureArgsIsMutable();
            this.args_.add(i, value);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void addAllArgs(Iterable<? extends Value> iterable) {
            ensureArgsIsMutable();
            AbstractMessageLite.addAll((Iterable) iterable, (List) this.args_);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void clearArgs() {
            this.args_ = emptyProtobufList();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void removeArgs(int i) {
            ensureArgsIsMutable();
            this.args_.remove(i);
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

        @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
        public int getOptionsCount() {
            return internalGetOptions().size();
        }

        @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
        public boolean containsOptions(String str) {
            str.getClass();
            return internalGetOptions().containsKey(str);
        }

        @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
        @Deprecated
        public Map<String, Value> getOptions() {
            return getOptionsMap();
        }

        @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
        public Map<String, Value> getOptionsMap() {
            return Collections.unmodifiableMap(internalGetOptions());
        }

        @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
        public Value getOptionsOrDefault(String str, Value value) {
            str.getClass();
            MapFieldLite<String, Value> mapFieldLiteInternalGetOptions = internalGetOptions();
            return mapFieldLiteInternalGetOptions.containsKey(str) ? mapFieldLiteInternalGetOptions.get(str) : value;
        }

        @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
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

        public static Stage parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
            return (Stage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
        }

        public static Stage parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Stage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
        }

        public static Stage parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
            return (Stage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
        }

        public static Stage parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Stage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
        }

        public static Stage parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
            return (Stage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
        }

        public static Stage parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
            return (Stage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
        }

        public static Stage parseFrom(InputStream inputStream) throws IOException {
            return (Stage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Stage parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Stage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Stage parseDelimitedFrom(InputStream inputStream) throws IOException {
            return (Stage) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
        }

        public static Stage parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Stage) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
        }

        public static Stage parseFrom(CodedInputStream codedInputStream) throws IOException {
            return (Stage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
        }

        public static Stage parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
            return (Stage) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
        }

        public static Builder newBuilder() {
            return DEFAULT_INSTANCE.createBuilder();
        }

        public static Builder newBuilder(Stage stage) {
            return DEFAULT_INSTANCE.createBuilder(stage);
        }

        public static final class Builder extends GeneratedMessageLite.Builder<Stage, Builder> implements StageOrBuilder {
            /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
                this();
            }

            private Builder() {
                super(Stage.DEFAULT_INSTANCE);
            }

            @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
            public String getName() {
                return ((Stage) this.instance).getName();
            }

            @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
            public ByteString getNameBytes() {
                return ((Stage) this.instance).getNameBytes();
            }

            public Builder setName(String str) {
                copyOnWrite();
                ((Stage) this.instance).setName(str);
                return this;
            }

            public Builder clearName() {
                copyOnWrite();
                ((Stage) this.instance).clearName();
                return this;
            }

            public Builder setNameBytes(ByteString byteString) {
                copyOnWrite();
                ((Stage) this.instance).setNameBytes(byteString);
                return this;
            }

            @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
            public List<Value> getArgsList() {
                return Collections.unmodifiableList(((Stage) this.instance).getArgsList());
            }

            @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
            public int getArgsCount() {
                return ((Stage) this.instance).getArgsCount();
            }

            @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
            public Value getArgs(int i) {
                return ((Stage) this.instance).getArgs(i);
            }

            public Builder setArgs(int i, Value value) {
                copyOnWrite();
                ((Stage) this.instance).setArgs(i, value);
                return this;
            }

            public Builder setArgs(int i, Value.Builder builder) {
                copyOnWrite();
                ((Stage) this.instance).setArgs(i, builder.build());
                return this;
            }

            public Builder addArgs(Value value) {
                copyOnWrite();
                ((Stage) this.instance).addArgs(value);
                return this;
            }

            public Builder addArgs(int i, Value value) {
                copyOnWrite();
                ((Stage) this.instance).addArgs(i, value);
                return this;
            }

            public Builder addArgs(Value.Builder builder) {
                copyOnWrite();
                ((Stage) this.instance).addArgs(builder.build());
                return this;
            }

            public Builder addArgs(int i, Value.Builder builder) {
                copyOnWrite();
                ((Stage) this.instance).addArgs(i, builder.build());
                return this;
            }

            public Builder addAllArgs(Iterable<? extends Value> iterable) {
                copyOnWrite();
                ((Stage) this.instance).addAllArgs(iterable);
                return this;
            }

            public Builder clearArgs() {
                copyOnWrite();
                ((Stage) this.instance).clearArgs();
                return this;
            }

            public Builder removeArgs(int i) {
                copyOnWrite();
                ((Stage) this.instance).removeArgs(i);
                return this;
            }

            @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
            public int getOptionsCount() {
                return ((Stage) this.instance).getOptionsMap().size();
            }

            @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
            public boolean containsOptions(String str) {
                str.getClass();
                return ((Stage) this.instance).getOptionsMap().containsKey(str);
            }

            public Builder clearOptions() {
                copyOnWrite();
                ((Stage) this.instance).getMutableOptionsMap().clear();
                return this;
            }

            public Builder removeOptions(String str) {
                str.getClass();
                copyOnWrite();
                ((Stage) this.instance).getMutableOptionsMap().remove(str);
                return this;
            }

            @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
            @Deprecated
            public Map<String, Value> getOptions() {
                return getOptionsMap();
            }

            @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
            public Map<String, Value> getOptionsMap() {
                return Collections.unmodifiableMap(((Stage) this.instance).getOptionsMap());
            }

            @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
            public Value getOptionsOrDefault(String str, Value value) {
                str.getClass();
                Map<String, Value> optionsMap = ((Stage) this.instance).getOptionsMap();
                return optionsMap.containsKey(str) ? optionsMap.get(str) : value;
            }

            @Override // com.google.firestore.v1.Pipeline.StageOrBuilder
            public Value getOptionsOrThrow(String str) {
                str.getClass();
                Map<String, Value> optionsMap = ((Stage) this.instance).getOptionsMap();
                if (!optionsMap.containsKey(str)) {
                    throw new IllegalArgumentException();
                }
                return optionsMap.get(str);
            }

            public Builder putOptions(String str, Value value) {
                str.getClass();
                value.getClass();
                copyOnWrite();
                ((Stage) this.instance).getMutableOptionsMap().put(str, value);
                return this;
            }

            public Builder putAllOptions(Map<String, Value> map) {
                copyOnWrite();
                ((Stage) this.instance).getMutableOptionsMap().putAll(map);
                return this;
            }
        }

        @Override // com.google.protobuf.GeneratedMessageLite
        protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
            Parser defaultInstanceBasedParser;
            AnonymousClass1 anonymousClass1 = null;
            switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
                case 1:
                    return new Stage();
                case 2:
                    return new Builder(anonymousClass1);
                case 3:
                    return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0001\u0001\u0000\u0001Ȉ\u0002\u001b\u00032", new Object[]{"name_", "args_", Value.class, "options_", OptionsDefaultEntryHolder.defaultEntry});
                case 4:
                    return DEFAULT_INSTANCE;
                case 5:
                    Parser<Stage> parser = PARSER;
                    if (parser != null) {
                        return parser;
                    }
                    synchronized (Stage.class) {
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
            Stage stage = new Stage();
            DEFAULT_INSTANCE = stage;
            GeneratedMessageLite.registerDefaultInstance(Stage.class, stage);
        }

        public static Stage getDefaultInstance() {
            return DEFAULT_INSTANCE;
        }

        public static Parser<Stage> parser() {
            return DEFAULT_INSTANCE.getParserForType();
        }
    }

    /* JADX INFO: renamed from: com.google.firestore.v1.Pipeline$1, reason: invalid class name */
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

    @Override // com.google.firestore.v1.PipelineOrBuilder
    public List<Stage> getStagesList() {
        return this.stages_;
    }

    public List<? extends StageOrBuilder> getStagesOrBuilderList() {
        return this.stages_;
    }

    @Override // com.google.firestore.v1.PipelineOrBuilder
    public int getStagesCount() {
        return this.stages_.size();
    }

    @Override // com.google.firestore.v1.PipelineOrBuilder
    public Stage getStages(int i) {
        return this.stages_.get(i);
    }

    public StageOrBuilder getStagesOrBuilder(int i) {
        return this.stages_.get(i);
    }

    private void ensureStagesIsMutable() {
        Internal.ProtobufList<Stage> protobufList = this.stages_;
        if (protobufList.isModifiable()) {
            return;
        }
        this.stages_ = GeneratedMessageLite.mutableCopy(protobufList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setStages(int i, Stage stage) {
        stage.getClass();
        ensureStagesIsMutable();
        this.stages_.set(i, stage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addStages(Stage stage) {
        stage.getClass();
        ensureStagesIsMutable();
        this.stages_.add(stage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addStages(int i, Stage stage) {
        stage.getClass();
        ensureStagesIsMutable();
        this.stages_.add(i, stage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAllStages(Iterable<? extends Stage> iterable) {
        ensureStagesIsMutable();
        AbstractMessageLite.addAll((Iterable) iterable, (List) this.stages_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearStages() {
        this.stages_ = emptyProtobufList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void removeStages(int i) {
        ensureStagesIsMutable();
        this.stages_.remove(i);
    }

    public static Pipeline parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (Pipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static Pipeline parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Pipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static Pipeline parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (Pipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static Pipeline parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Pipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static Pipeline parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (Pipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static Pipeline parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (Pipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static Pipeline parseFrom(InputStream inputStream) throws IOException {
        return (Pipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Pipeline parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Pipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Pipeline parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (Pipeline) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static Pipeline parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Pipeline) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static Pipeline parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (Pipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static Pipeline parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (Pipeline) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(Pipeline pipeline) {
        return DEFAULT_INSTANCE.createBuilder(pipeline);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<Pipeline, Builder> implements PipelineOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(Pipeline.DEFAULT_INSTANCE);
        }

        @Override // com.google.firestore.v1.PipelineOrBuilder
        public List<Stage> getStagesList() {
            return Collections.unmodifiableList(((Pipeline) this.instance).getStagesList());
        }

        @Override // com.google.firestore.v1.PipelineOrBuilder
        public int getStagesCount() {
            return ((Pipeline) this.instance).getStagesCount();
        }

        @Override // com.google.firestore.v1.PipelineOrBuilder
        public Stage getStages(int i) {
            return ((Pipeline) this.instance).getStages(i);
        }

        public Builder setStages(int i, Stage stage) {
            copyOnWrite();
            ((Pipeline) this.instance).setStages(i, stage);
            return this;
        }

        public Builder setStages(int i, Stage.Builder builder) {
            copyOnWrite();
            ((Pipeline) this.instance).setStages(i, builder.build());
            return this;
        }

        public Builder addStages(Stage stage) {
            copyOnWrite();
            ((Pipeline) this.instance).addStages(stage);
            return this;
        }

        public Builder addStages(int i, Stage stage) {
            copyOnWrite();
            ((Pipeline) this.instance).addStages(i, stage);
            return this;
        }

        public Builder addStages(Stage.Builder builder) {
            copyOnWrite();
            ((Pipeline) this.instance).addStages(builder.build());
            return this;
        }

        public Builder addStages(int i, Stage.Builder builder) {
            copyOnWrite();
            ((Pipeline) this.instance).addStages(i, builder.build());
            return this;
        }

        public Builder addAllStages(Iterable<? extends Stage> iterable) {
            copyOnWrite();
            ((Pipeline) this.instance).addAllStages(iterable);
            return this;
        }

        public Builder clearStages() {
            copyOnWrite();
            ((Pipeline) this.instance).clearStages();
            return this;
        }

        public Builder removeStages(int i) {
            copyOnWrite();
            ((Pipeline) this.instance).removeStages(i);
            return this;
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        Parser defaultInstanceBasedParser;
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new Pipeline();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"stages_", Stage.class});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<Pipeline> parser = PARSER;
                if (parser != null) {
                    return parser;
                }
                synchronized (Pipeline.class) {
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
        Pipeline pipeline = new Pipeline();
        DEFAULT_INSTANCE = pipeline;
        GeneratedMessageLite.registerDefaultInstance(Pipeline.class, pipeline);
    }

    public static Pipeline getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<Pipeline> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
