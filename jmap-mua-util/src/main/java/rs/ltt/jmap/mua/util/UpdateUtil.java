/*
 * Copyright 2019 Daniel Gultsch
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package rs.ltt.jmap.mua.util;

import com.google.common.util.concurrent.ListenableFuture;
import rs.ltt.jmap.client.JmapClient;
import rs.ltt.jmap.client.JmapRequest;
import rs.ltt.jmap.client.MethodResponses;
import rs.ltt.jmap.common.Request;
import rs.ltt.jmap.common.entity.Email;
import rs.ltt.jmap.common.method.call.email.ChangesEmailMethodCall;
import rs.ltt.jmap.common.method.call.email.GetEmailMethodCall;
import rs.ltt.jmap.common.method.call.identity.ChangesIdentityMethodCall;
import rs.ltt.jmap.common.method.call.identity.GetIdentityMethodCall;
import rs.ltt.jmap.common.method.call.mailbox.ChangesMailboxMethodCall;
import rs.ltt.jmap.common.method.call.mailbox.GetMailboxMethodCall;
import rs.ltt.jmap.common.method.call.thread.ChangesThreadMethodCall;
import rs.ltt.jmap.common.method.call.thread.GetThreadMethodCall;

public class UpdateUtil {

    public static MethodResponsesFuture emails(JmapClient.MultiCall multiCall, String accountId, String state) {
        final JmapRequest.Call changesCallInfo = multiCall.call(
                ChangesEmailMethodCall.builder()
                        .accountId(accountId)
                        .sinceState(state)
                        .build()
        );
        final ListenableFuture<MethodResponses> changes = changesCallInfo.getMethodResponses();
        final ListenableFuture<MethodResponses> created = multiCall.call(
                GetEmailMethodCall.builder()
                        .accountId(accountId)
                        .idsReference(changesCallInfo.createResultReference(Request.Invocation.ResultReference.Path.CREATED))
                        .fetchTextBodyValues(true)
                        .build()
        ).getMethodResponses();
        final ListenableFuture<MethodResponses> updated = multiCall.call(
                GetEmailMethodCall.builder()
                        .accountId(accountId)
                        .idsReference(changesCallInfo.createResultReference(Request.Invocation.ResultReference.Path.UPDATED))
                        .properties(Email.Properties.MUTABLE)
                        .build()
        ).getMethodResponses();

        return new MethodResponsesFuture(changes, created, updated);
    }

    public static MethodResponsesFuture identities(JmapClient.MultiCall multiCall, String accountId, String state) {
        final JmapRequest.Call changesCallInfo = multiCall.call(
                ChangesIdentityMethodCall.builder()
                        .accountId(accountId)
                        .sinceState(state)
                        .build()
        );
        final ListenableFuture<MethodResponses> changes = changesCallInfo.getMethodResponses();
        final ListenableFuture<MethodResponses> created = multiCall.call(
                GetIdentityMethodCall.builder()
                        .accountId(accountId)
                        .idsReference(changesCallInfo.createResultReference(Request.Invocation.ResultReference.Path.CREATED))
                        .build()
        ).getMethodResponses();
        final ListenableFuture<MethodResponses> updated = multiCall.call(
                GetIdentityMethodCall.builder()
                        .accountId(accountId)
                        .idsReference(changesCallInfo.createResultReference(Request.Invocation.ResultReference.Path.UPDATED))
                        .build()
        ).getMethodResponses();

        return new MethodResponsesFuture(changes, created, updated);
    }

    public static MethodResponsesFuture mailboxes(JmapClient.MultiCall multiCall, String accountId, String state) {
        final JmapRequest.Call changesCallInfo = multiCall.call(
                ChangesMailboxMethodCall.builder()
                        .accountId(accountId)
                        .sinceState(state)
                        .build()
        );
        final ListenableFuture<MethodResponses> changes = changesCallInfo.getMethodResponses();
        final ListenableFuture<MethodResponses> created = multiCall.call(
                GetMailboxMethodCall.builder()
                        .accountId(accountId)
                        .idsReference(changesCallInfo.createResultReference(Request.Invocation.ResultReference.Path.CREATED))
                        .build()
        ).getMethodResponses();
        final ListenableFuture<MethodResponses> updated = multiCall.call(
                GetMailboxMethodCall.builder()
                        .accountId(accountId)
                        .idsReference(changesCallInfo.createResultReference(Request.Invocation.ResultReference.Path.UPDATED))
                        .propertiesReference(changesCallInfo.createResultReference(Request.Invocation.ResultReference.Path.UPDATED_PROPERTIES))
                        .build()
        ).getMethodResponses();

        return new MethodResponsesFuture(changes, created, updated);
    }

    public static MethodResponsesFuture threads(JmapClient.MultiCall multiCall, String accountId, String state) {
        final JmapRequest.Call changesCallInfo = multiCall.call(
                ChangesThreadMethodCall.builder()
                        .accountId(accountId)
                        .sinceState(state)
                        .build()
        );
        final ListenableFuture<MethodResponses> changes = changesCallInfo.getMethodResponses();
        final ListenableFuture<MethodResponses> created = multiCall.call(
                GetThreadMethodCall.builder()
                        .accountId(accountId)
                        .idsReference(changesCallInfo.createResultReference(Request.Invocation.ResultReference.Path.CREATED))
                        .build()
        ).getMethodResponses();
        final ListenableFuture<MethodResponses> updated = multiCall.call(
                GetThreadMethodCall.builder()
                        .accountId(accountId)
                        .idsReference(changesCallInfo.createResultReference(Request.Invocation.ResultReference.Path.UPDATED))
                        .build()
        ).getMethodResponses();

        return new MethodResponsesFuture(changes, created, updated);
    }

    public static class MethodResponsesFuture {
        public final ListenableFuture<MethodResponses> changes;
        public final ListenableFuture<MethodResponses> created;
        public final ListenableFuture<MethodResponses> updated;

        private MethodResponsesFuture(ListenableFuture<MethodResponses> changes, ListenableFuture<MethodResponses> created, ListenableFuture<MethodResponses> updated) {
            this.changes = changes;
            this.created = created;
            this.updated = updated;
        }
    }

}
