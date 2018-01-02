import ballerina.test;

public function testAssertEqualString() {
    string string1 = "Hello";
    string string2 = "Hello";
    test:assertEquals(string1, string2, "Strings does not match");
}