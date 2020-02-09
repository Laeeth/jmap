/*
 * Copyright 2020 Daniel Gultsch
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

package rs.ltt.jmap.common.method.call.core;

import rs.ltt.jmap.annotation.JmapMethod;
import rs.ltt.jmap.common.Request;
import rs.ltt.jmap.common.entity.PushSubscription;
import rs.ltt.jmap.common.method.call.standard.SetMethodCall;

import java.util.Map;

@JmapMethod("PushSubscription/set")
public class SetPushSubscriptionMethodCall extends SetMethodCall<PushSubscription> {
    public SetPushSubscriptionMethodCall(String accountId, String ifInState, Map<String, PushSubscription> create, Map<String, Map<String, Object>> update, String[] destroy) {
        super(accountId, ifInState, create, update, destroy);
    }

    public SetPushSubscriptionMethodCall(String accountId, String ifInState, String[] destroy) {
        super(accountId, ifInState, destroy);
    }

    public SetPushSubscriptionMethodCall(String accountId, String ifInState, Request.Invocation.ResultReference destroy) {
        super(accountId, ifInState, destroy);
    }

    public SetPushSubscriptionMethodCall(String accountId, String ifInState, Map<String, Map<String, Object>> update) {
        super(accountId, ifInState, update);
    }

    public SetPushSubscriptionMethodCall(String accountId, Map<String, PushSubscription> create) {
        super(accountId, create);
    }
}