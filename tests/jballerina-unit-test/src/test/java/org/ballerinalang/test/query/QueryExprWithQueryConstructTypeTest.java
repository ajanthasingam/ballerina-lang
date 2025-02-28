/*
 *  Copyright (c) 2020, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.ballerinalang.test.query;

import io.ballerina.runtime.internal.util.exceptions.BLangRuntimeException;
import org.ballerinalang.test.BCompileUtil;
import org.ballerinalang.test.BRunUtil;
import org.ballerinalang.test.CompileResult;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.ballerinalang.test.BAssertUtil.validateError;

/**
 * This contains methods to test query expression with query construct type.
 *
 * @since 1.3.0
 */
public class QueryExprWithQueryConstructTypeTest {

    private CompileResult result, negativeResult, semanticsNegativeResult;

    @BeforeClass
    public void setup() {
        result = BCompileUtil.compile("test-src/query/query-expr-with-query-construct-type.bal");
        negativeResult = BCompileUtil.compile("test-src/query/query-expr-query-construct-type-negative.bal");
        semanticsNegativeResult = BCompileUtil.compile("test-src/query/query-expr-query-construct-type-semantics" +
                "-negative.bal");
    }

    @Test(description = "Test query expr returning a stream ")
    public void testSimpleQueryReturnStream() {
        Object returnValues = BRunUtil.invoke(result, "testSimpleQueryReturnStream");
        Assert.assertNotNull(returnValues);

        Assert.assertTrue((Boolean) returnValues);
    }

    @Test(description = "Test query expr with stream in from clause returning a stream ")
    public void testStreamInFromClauseWithReturnStream() {
        Object returnValues = BRunUtil.invoke(result, "testStreamInFromClauseWithReturnStream");
        Assert.assertNotNull(returnValues);

        Assert.assertTrue((Boolean) returnValues);
    }

    @Test(description = "Test query expr with multiple from, let and where clauses returning a stream ")
    public void testMultipleFromWhereAndLetReturnStream() {
        Object returnValues = BRunUtil.invoke(result, "testMultipleFromWhereAndLetReturnStream");
        Assert.assertNotNull(returnValues);

        Assert.assertTrue((Boolean) returnValues);
    }

    @Test(description = "Test query expr with inner join returning a stream ")
    public void testInnerJoinAndLimitReturnStream() {
        Object returnValues = BRunUtil.invoke(result, "testInnerJoinAndLimitReturnStream");
        Assert.assertNotNull(returnValues);

        Assert.assertTrue((Boolean) returnValues);
    }

    @Test(description = "Test query expr returning table")
    public void testSimpleQueryExprReturnTable() {
        Object returnValues = BRunUtil.invoke(result, "testSimpleQueryExprReturnTable");
        Assert.assertNotNull(returnValues);

        Assert.assertTrue((Boolean) returnValues);
    }

    @Test(description = "Test query expr with table having duplicate keys")
    public void testTableWithDuplicateKeys() {
        BRunUtil.invoke(result, "testTableWithDuplicateKeys");
    }

    @Test(description = "Test query expr with table having no duplicates and on conflict clause")
    public void testTableNoDuplicatesAndOnConflictReturnTable() {
        Object returnValues = BRunUtil.invoke(result, "testTableNoDuplicatesAndOnConflictReturnTable");
        Assert.assertNotNull(returnValues);

        Assert.assertTrue((Boolean) returnValues);
    }

    @Test(description = "Test query expr with table having duplicate keys")
    public void testTableWithDuplicatesAndOnConflictReturnTable() {
        BRunUtil.invoke(result, "testTableWithDuplicatesAndOnConflictReturnTable");
    }

    @Test(description = "Test query expr with table having duplicate keys")
    public void testQueryExprWithOtherClausesReturnTable() {
        BRunUtil.invoke(result, "testQueryExprWithOtherClausesReturnTable");
    }

    @Test(description = "Test query expr with table having duplicate keys")
    public void testQueryExprWithJoinClauseReturnTable() {
        BRunUtil.invoke(result, "testQueryExprWithJoinClauseReturnTable");
    }

    @Test(description = "Test query expr with table having no duplicates and on conflict clause")
    public void testQueryExprWithLimitClauseReturnTable() {
        Object returnValues = BRunUtil.invoke(result, "testQueryExprWithLimitClauseReturnTable");
        Assert.assertNotNull(returnValues);

        Assert.assertTrue((Boolean) returnValues);
    }

    @Test(description = "Test query expr with table having no duplicates and on conflict clause")
    public void testKeyLessTableWithReturnTable() {
        BRunUtil.invoke(result, "testKeyLessTableWithReturnTable");
        Object returnValues = BRunUtil.invoke(result, "testKeyLessTableWithReturnTable");
        Assert.assertNotNull(returnValues);

        Assert.assertTrue((Boolean) returnValues);
    }

    @Test(description = "Test negative scenarios for query expr with query construct type")
    public void testNegativeScenarios() {
        int index = 0;

        validateError(negativeResult, index++, "incompatible types: expected 'Person[]', found 'stream<Person>'",
                54, 35);
        validateError(negativeResult, index++, "incompatible types: expected 'Customer[]', " +
                        "found 'table<Customer> key(id, name)'",
                71, 32);
        validateError(negativeResult, index++, "incompatible types: expected 'error?', found 'boolean'",
                107, 21);
        validateError(negativeResult, index++,
                "incompatible type in select clause: expected [string,any|error], found 'User'", 126, 25);
        validateError(negativeResult, index++,
                "incompatible type in select clause: expected [string,any|error], found '[int,User]'", 130, 25);
        validateError(negativeResult, index++,
                "incompatible type in select clause: expected [string,any|error], found 'int[2]'", 135, 25);
        validateError(negativeResult, index++,
                "incompatible type in select clause: expected [string,any|error], found 'string[]'", 140, 25);
        validateError(negativeResult, index++,
                "incompatible type in select clause: expected [string,any|error], found 'User'", 148, 25);
        validateError(negativeResult, index++,
                "incompatible type in select clause: expected [string,any|error], found '[int,User]'", 152, 25);
        validateError(negativeResult, index++,
                "incompatible type in select clause: expected [string,any|error], found 'int[2]'", 157, 25);
        validateError(negativeResult, index++,
                "incompatible type in select clause: expected [string,any|error], found 'string[]'", 162, 25);
        validateError(negativeResult, index++,
                "incompatible type in select clause: expected [string,any|error], found 'string[3]'", 167, 25);
        validateError(negativeResult, index++,
                "incompatible type in select clause: expected [string,any|error], found '[string]'", 171, 25);
        validateError(negativeResult, index++,
                "incompatible types: expected 'int', found 'string'", 180, 50);
        validateError(negativeResult, index++,
                "incompatible types: expected 'User', found 'int'", 184, 46);
        validateError(negativeResult, index++,
                "incompatible types: expected 'string', found 'int'", 188, 59);
        validateError(negativeResult, index++,
                "incompatible types: expected 'int', found 'string'", 193, 50);
        validateError(negativeResult, index++,
                "incompatible types: expected 'string', found 'User'", 197, 46);
        validateError(negativeResult, index++,
                "incompatible types: expected '[string,User]', found 'string[2]'", 202, 29);
        validateError(negativeResult, index++,
                "incompatible types: expected '[string,string]', found '(string[2]|[string,int])'", 207, 29);
        validateError(negativeResult, index++,
                "incompatible type in select clause: expected [string,any|error], found 'int[2] & readonly'", 217, 29);
        validateError(negativeResult, index++,
                "incompatible type in select clause: expected [string,any|error], found 'int[2]'", 222, 29);
        validateError(negativeResult, index++,
                "incompatible types: expected '([string,int]|[string,string])', found '(string|int)'", 227, 56);
        validateError(negativeResult, index++,
                "incompatible types: expected 'map<string>', found 'map<(int|string)>'", 229, 21);
        validateError(negativeResult, index++, "missing non-defaultable required record field 'noOfItems'",
                236, 16);
        validateError(negativeResult, index++,
                "incompatible types: expected '(Customer & readonly)', found 'Customer'", 242, 16);
        validateError(negativeResult, index++,
                "incompatible types: expected 'string', found '(int|string)'", 252, 63);
        validateError(negativeResult, index++,
                "incompatible types: expected '(Type1 & readonly)', found '([int,int]|string|[int,int])'", 255, 44);
        validateError(negativeResult, index++,
                "incompatible types: expected '(Type1 & readonly)', found '([int,int]|string|[int,int])'", 258, 51);
        validateError(negativeResult, index++,
                "incompatible types: expected 'xml<((xml:Element|xml:Comment|xml:ProcessingInstruction|xml:Text) " +
                     "& readonly)> & readonly', found '(xml:Element|xml:Comment|xml:ProcessingInstruction|xml:Text)'",
                263, 41);
        validateError(negativeResult, index++,
                "incompatible types: expected 'int[2] & readonly', found 'int[2]'", 279, 69);
        validateError(negativeResult, index++,
                "incompatible types: expected '(Department & readonly)', found 'Department'", 283, 55);
        validateError(negativeResult, index++, "incompatible types: expected '[string,string]', " +
                "found '([string,int]|[string,int]|[string,int]|[string,int])'", 286, 48);
        validateError(negativeResult, index++, "incompatible types: expected 'map<(int[2] & readonly)> & readonly'," +
                " found '((map<(int[2] & readonly)> & readonly)|error)'", 292, 34);
        validateError(negativeResult, index++, "incompatible types: expected '(Department & readonly)'," +
                " found 'Department'", 297, 55);
        validateError(negativeResult, index++, "incompatible types: expected '[string,string]', " +
                "found '([string,int]|[string,int]|[string,int]|[string,int])'", 300, 48);
        validateError(negativeResult, index++, "missing non-defaultable required record field 'noOfItems'", 310, 16);
        validateError(negativeResult, index++, "incompatible types: expected 'table<(Customer & readonly)> & " + "" +
                "readonly', found '((table<(Customer & readonly)> & readonly)|error)'", 319, 44);
        validateError(negativeResult, index++, "incompatible types: expected 'map<int>', found '(map<int>|error)'",
                347, 21);
        validateError(negativeResult, index++, "incompatible types: expected 'table<ResultValue>', " + "" +
                        "found '(table<ResultValue>|error)'", 350, 33);
        validateError(negativeResult, index++, "incompatible types: expected 'table<NumberRecord> key(id)', " +
                        "found '(table<NumberRecord> key(id)|error)'", 353, 42);
        // Enable following tests after fixing issue - lang/#36746
//        validateError(negativeResult, index++, "incompatible types: expected 'map<int>', found '(map<int>|error)'",
//                356, 21);
//        validateError(negativeResult, index++, "incompatible types: expected 'table<NumberRecord>', " +
//                "found '(table<NumberRecord>|error)'", 361, 40);
//        validateError(negativeResult, index++, "incompatible types: expected 'table<NumberRecord> key(id)', " +
//                "found '(table<NumberRecord> key(id)|error)'", 366, 42);
        validateError(negativeResult, index++, "incompatible types: expected 'map<int>', found '(map<int>|error)'",
                371, 21);
        validateError(negativeResult, index++, "incompatible types: expected 'table<NumberRecord> key(id)', " +
                "found '(table<NumberRecord> key(id)|error)'", 374, 42);
        validateError(negativeResult, index++,
                "incompatible types: expected 'map<int>', found '(map<int>|error)'", 382, 21);
        validateError(negativeResult, index++,
                "incompatible types: expected 'table<ResultValue>', found '(table<ResultValue>|error)'", 385, 33);
        validateError(negativeResult, index++, "incompatible types: expected 'table<NumberRecord> key(id)', " +
                        "found '(table<NumberRecord> key(id)|error)'", 388, 42);
        validateError(negativeResult, index++,
                "incompatible types: '(map<int>|error)' is not an iterable collection", 391, 48);
        validateError(negativeResult, index++,
                "incompatible types: '(table<record {| readonly int id; string value; |}> key(id)|error)' " +
                        "is not an iterable collection", 395, 100);
        Assert.assertEquals(negativeResult.getErrorCount(), index);
    }

    @Test(description = "Test semantic negative scenarios for query expr with query construct type")
    public void testSemanticNegativeScenarios() {
        int index = 0;
        validateError(semanticsNegativeResult, index++, "on conflict can only be used with queries which produce " +
                        "maps or tables with key specifiers", 39, 13);
        validateError(semanticsNegativeResult, index++, "on conflict can only be used with queries which produce " +
                "maps or tables with key specifiers", 59, 9);
        validateError(semanticsNegativeResult, index++, "on conflict can only be used with queries which produce " +
                "maps or tables with key specifiers", 71, 9);
        validateError(semanticsNegativeResult, index++, "on conflict can only be used with queries which produce " +
                "maps or tables with key specifiers", 84, 9);
        validateError(semanticsNegativeResult, index++, "on conflict can only be used with queries which produce " +
                "maps or tables with key specifiers", 95, 9);
        validateError(semanticsNegativeResult, index++, "on conflict can only be used with queries which produce " +
                "maps or tables with key specifiers", 119, 9);
        validateError(semanticsNegativeResult, index++, "on conflict can only be used with queries which produce " +
                "maps or tables with key specifiers", 126, 47);
        validateError(semanticsNegativeResult, index++, "on conflict can only be used with queries which produce " +
                "maps or tables with key specifiers", 131, 9);
        validateError(semanticsNegativeResult, index++, "on conflict can only be used with queries which produce " +
                "maps or tables with key specifiers", 144, 9);
        Assert.assertEquals(semanticsNegativeResult.getErrorCount(), index);
    }

    @Test(expectedExceptions = BLangRuntimeException.class,
            expectedExceptionsMessageRegExp = "error: \\{ballerina/lang.map\\}InherentTypeViolation " +
                    "\\{\"message\":\"cannot update 'readonly' field 'id' in record of type 'record " +
                    "\\{\\| readonly int id; readonly string name; User user; \\|\\}'\".*")
    public void testQueryConstructingTableUpdateKeyPanic1() {
        BRunUtil.invoke(result, "testQueryConstructingTableUpdateKeyPanic1");
    }

    @Test(expectedExceptions = BLangRuntimeException.class,
            expectedExceptionsMessageRegExp = "error: \\{ballerina/lang.map\\}InherentTypeViolation " +
                    "\\{\"message\":\"cannot update 'readonly' field 'id' in record of type 'record " +
                    "\\{\\| readonly int id; readonly string name; User user; \\|\\}'\".*")
    public void testQueryConstructingTableUpdateKeyPanic2() {
        BRunUtil.invoke(result, "testQueryConstructingTableUpdateKeyPanic2");
    }

    @Test
    public void testTableOnConflict() {
        BRunUtil.invoke(result, "testTableOnConflict");
    }

    @Test(dataProvider = "dataToTestQueryConstructingTable")
    public void testQueryConstructingTable(String functionName) {
        BRunUtil.invoke(result, functionName);
    }

    @DataProvider
    public Object[] dataToTestQueryConstructingTable() {
        return new Object[]{
                "testQueryConstructingTableWithOnConflictClauseHavingNonTableQueryInLetClause",
                "testQueryConstructingTableWithOnConflictClauseHavingNonTableQueryInWhereClause"
        };
    }

    @Test
    public void testReadonlyTable() {
        BRunUtil.invoke(result, "testReadonlyTable");
    }

    @Test
    public void testReadonlyTable2() {
        BRunUtil.invoke(result, "testReadonlyTable2");
    }

    @Test
    public void testReadonlyTable3() {
        BRunUtil.invoke(result, "testReadonlyTable3");
    }

    @Test
    public void testConstructingListOfTablesUsingQueryWithReadonly() {
        BRunUtil.invoke(result, "testConstructingListOfTablesUsingQueryWithReadonly");
    }

    @Test
    public void testConstructingListOfXMLsUsingQueryWithReadonly() {
        BRunUtil.invoke(result, "testConstructingListOfXMLsUsingQueryWithReadonly");
    }

    @Test
    public void testConstructingListOfRecordsUsingQueryWithReadonly() {
        BRunUtil.invoke(result, "testConstructingListOfRecordsUsingQueryWithReadonly");
    }

    @Test
    public void testConstructingListOfListsUsingQueryWithReadonly() {
        BRunUtil.invoke(result, "testConstructingListOfListsUsingQueryWithReadonly");
    }

    @Test
    public void testConstructingListOfMapsUsingQueryWithReadonly() {
        BRunUtil.invoke(result, "testConstructingListOfMapsUsingQueryWithReadonly");
    }

    @Test
    public void testConstructingListInRecordsUsingQueryWithReadonly() {
        BRunUtil.invoke(result, "testConstructingListInRecordsUsingQueryWithReadonly");
    }

    @Test
    public void testReadonlyMap1() {
        BRunUtil.invoke(result, "testReadonlyMap1");
    }

    @Test
    public void testReadonlyMap2() {
        BRunUtil.invoke(result, "testReadonlyMap2");
    }

    @Test
    public void testQueryConstructingMapsAndTablesWithClausesMayCompleteSEarlyWithError() {
        BRunUtil.invoke(result, "testQueryConstructingMapsAndTablesWithClausesMayCompleteSEarlyWithError");
    }

    @Test
    public void testQueryConstructingMapsAndTablesWithClausesMayCompleteSEarlyWithError2() {
        BRunUtil.invoke(result, "testQueryConstructingMapsAndTablesWithClausesMayCompleteSEarlyWithError2");
    }

    @AfterClass
    public void tearDown() {
        result = null;
        negativeResult = null;
        semanticsNegativeResult = null;
    }
}
