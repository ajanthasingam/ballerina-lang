/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package org.ballerinalang.test.types.decimaltype;

import org.ballerinalang.test.BAssertUtil;
import org.ballerinalang.test.BCompileUtil;
import org.ballerinalang.test.CompileResult;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * This test class contains negative test cases for decimal value.
 *
 * @since 0.985.0
 */
public class BDecimalValueNegativeTest {
    @Test
    public void testDecimalValue() {
        CompileResult compileResult = BCompileUtil.compile("test-src/types/decimal/decimal_value_negative.bal");
        int index = 0;
        BAssertUtil.validateError(compileResult, index++, "missing semicolon token", 21, 1);
        BAssertUtil.validateError(compileResult, index++, "missing binary operator", 23, 21);
        BAssertUtil.validateError(compileResult, index++, "undefined symbol 'g'", 23, 21);
        BAssertUtil.validateError(compileResult, index++, "leading zeros in numeric literals", 26, 17);
        BAssertUtil.validateError(compileResult, index++, "missing hex number after hex indicator", 29, 18);
        BAssertUtil.validateError(compileResult, index++, "invalid expression statement", 29, 20);
        BAssertUtil.validateError(compileResult, index++, "missing semicolon token", 29, 20);
        BAssertUtil.validateError(compileResult, index++, "undefined symbol 'X1231'", 29, 20);
        BAssertUtil.validateError(compileResult, index++, "missing semicolon token", 29, 25);
        BAssertUtil.validateError(compileResult, index++, "invalid expression statement", 32, 20);
        BAssertUtil.validateError(compileResult, index++, "missing semicolon token", 32, 20);
        BAssertUtil.validateError(compileResult, index++, "undefined symbol 'X1231'", 32, 20);
        BAssertUtil.validateError(compileResult, index++, "missing semicolon token", 32, 25);
        BAssertUtil.validateError(compileResult, index++, "missing digit after dot", 35, 17);
        BAssertUtil.validateError(compileResult, index++, "missing digit after dot", 36, 17);
        BAssertUtil.validateError(compileResult, index++, "missing hex number after hex indicator", 39, 17);
        BAssertUtil.validateError(compileResult, index++, "missing hex number after hex indicator", 40, 17);
        BAssertUtil.validateError(compileResult, index++, "'9.99E+6111' is out of range for 'float'",
                44, 17);
        BAssertUtil.validateError(compileResult, index++, "'9.99E+6111' is out of range for 'float'",
                45, 26);
        Assert.assertEquals(compileResult.getErrorCount(), index);
    }

    @Test
    void testDecimalValueNegativeLiteral() {
        CompileResult negative = BCompileUtil.compile("test-src/types/decimal/decimal_value_negative_literal.bal");
        Assert.assertEquals(negative.getErrorCount(), 13);
        int i = 0;
        BAssertUtil.validateError(negative, i++, "incompatible types: expected 'decimal', found 'float'", 20, 17);
        BAssertUtil.validateError(negative, i++, "incompatible types: expected 'decimal', found 'float'", 21, 17);
        BAssertUtil.validateError(negative, i++, "incompatible types: expected 'decimal', found 'float'", 26, 22);
        BAssertUtil.validateError(negative, i++, "incompatible types: expected 'decimal', found 'float'", 27, 22);
        BAssertUtil.validateError(negative, i++, "incompatible types: expected 'decimal', found 'float'", 27, 31);
        BAssertUtil.validateError(negative, i++, "incompatible types: expected '(int|decimal)', found 'float'", 28, 23);
        BAssertUtil.validateError(negative, i++, "operator '/' not defined for 'decimal' and 'float'", 34, 17);
        BAssertUtil.validateError(negative, i++, "operator '+' not defined for 'decimal' and 'float'", 35, 17);
        BAssertUtil.validateError(negative, i++, "operator '+' not defined for 'decimal' and 'float'", 36, 17);
        BAssertUtil.validateError(negative, i++, "operator '*' not defined for 'decimal' and 'float'", 37, 17);
        BAssertUtil.validateError(negative, i++, "operator '+' not defined for 'float' and 'decimal'", 38, 17);
        BAssertUtil.validateError(negative, i++, "operator '-' not defined for 'float' and 'decimal'", 39, 17);
        BAssertUtil.validateError(negative, i++, "operator '+' not defined for 'decimal' and 'float'", 41, 17);
    }
}
