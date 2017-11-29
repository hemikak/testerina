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

package org.ballerinalang.testerina.test;

import org.ballerinalang.model.values.BInteger;
import org.ballerinalang.model.values.BValue;
import org.ballerinalang.testerina.test.utils.BTestUtils;
import org.ballerinalang.testerina.test.utils.CompileResult;
import org.ballerinalang.util.codegen.FunctionInfo;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 *
 */
public class AssertEquals {
    CompileResult compileResult;
    @BeforeClass
    public void setup() {
        compileResult = BTestUtils.compile("assert-equals-valid.bal");
    }
    
    @DataProvider(name = "AssertEqualsValidFunctionNames")
    public Object[][] functionNames() {
        
        FunctionInfo[] functionInfoEntries = compileResult.getProgFile().getPackageInfoEntries()[0]
                .getFunctionInfoEntries();
    
    
        return Arrays.stream(functionInfoEntries)
                .map(functionInfo -> new Object[]{functionInfo})
                .toArray(Object[][]::new);
        
    }
    
    @Test(dataProvider = "AssertEqualsValidFunctionNames")
    public void testAssertIntEquals(FunctionInfo assertFunction) {
        BTestUtils.invoke(compileResult, assertFunction.getName());
    }
}
