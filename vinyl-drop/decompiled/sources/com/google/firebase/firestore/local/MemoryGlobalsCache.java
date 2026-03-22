package com.google.firebase.firestore.local;

import com.google.protobuf.ByteString;

/* JADX INFO: loaded from: classes3.dex */
final class MemoryGlobalsCache implements GlobalsCache {
    private ByteString sessionToken = ByteString.EMPTY;

    MemoryGlobalsCache() {
    }

    @Override // com.google.firebase.firestore.local.GlobalsCache
    public ByteString getSessionsToken() {
        return this.sessionToken;
    }

    @Override // com.google.firebase.firestore.local.GlobalsCache
    public void setSessionToken(ByteString byteString) {
        this.sessionToken = byteString;
    }
}
