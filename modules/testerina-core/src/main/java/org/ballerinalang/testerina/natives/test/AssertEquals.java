/*
 * Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.ballerinalang.testerina.natives.test;

import org.ballerinalang.bre.Context;
import org.ballerinalang.model.types.TypeKind;
import org.ballerinalang.model.values.BString;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.natives.AbstractNativeFunction;
import org.ballerinalang.natives.annotations.Argument;
import org.ballerinalang.natives.annotations.BallerinaFunction;
import org.ballerinalang.util.exceptions.BLangRuntimeException;

/**
 *
 */
@BallerinaFunction(packageName = "ballerina.test", functionName = "assertEquals",
                   args = {
                        @Argument(name = "expected", type = TypeKind.ANY),
                        @Argument(name = "actual", type = TypeKind.ANY),
                        @Argument(name = "message", type = TypeKind.STRING)},
                   isPublic = true)
public class AssertEquals extends AbstractNativeFunction {
    @Override
    public BValue[] execute(Context context) {
        BValue expectedArgValue = context.getControlStackNew().getCurrentFrame().argValues[0];
        BValue acceptedArgValue = context.getControlStackNew().getCurrentFrame().argValues[1];
        
        // If the package of the types of the values are different - assert fail.
        if (!expectedArgValue.getType().getPackagePath().equals(acceptedArgValue.getType().getPackagePath())) {
            throwAssertError();
        }
    
        // If the types of the values are different - assert fail.
        if (!expectedArgValue.getType().getName().equals(acceptedArgValue.getType().getName())) {
            throwAssertError();
        }
        
        if (expectedArgValue instanceof BString) {
            if (!expectedArgValue.stringValue().equals(acceptedArgValue.stringValue())) {
                throwAssertError();
            }
        }
        return VOID_RETURN;
    }
    
    public void throwAssertError() {
        throw new BLangRuntimeException("Assert failed.");
    }
}
