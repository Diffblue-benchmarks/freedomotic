package com.freedomotic.rules;

import org.junit.Assert;
import org.junit.Test;

public class StatementTest {

  @Test
  public void createLogicalInputValid() {
    final Statement statement = new Statement().create(Statement.EQUALS, "test.value", Statement.AND, "100");
    Assert.assertNotNull(statement);
    Assert.assertEquals(Statement.EQUALS, statement.getLogical());
    Assert.assertEquals("test.value", statement.getAttribute());
    Assert.assertEquals(Statement.AND, statement.getOperand());
    Assert.assertEquals("100", statement.getValue());
  }

  @Test
  public void createLogicalInputInvalid() {
    final Statement statement = new Statement().create(Statement.NOT, "test.value", Statement.OR, "100");
    Assert.assertNotNull(statement);
    Assert.assertEquals(Statement.NOT, statement.getLogical());
    Assert.assertEquals("test.value", statement.getAttribute());
    Assert.assertEquals(Statement.OR, statement.getOperand());
    Assert.assertEquals("100", statement.getValue());
  }

  @Test
  public void createStatementNull() {
    Assert.assertNull(new Statement().create(" ", null, " ", null));
  }

  @Test
  public void statementEqualsTrue() {
    Assert.assertEquals(new Statement().create(null, "!", null, " "),
                        new Statement().create(null, "!", "!", null));
  }

  @Test
  public void statementEqualsFalse() {
    Assert.assertNotEquals(new Statement().create(" ", null, " ", null),
                           new Statement().create(Statement.LESS_THAN, "test.value", Statement.OR, "50"));
  }

  @Test
  public void equalsNullNotEqual() {
    Assert.assertNotEquals(new Statement(), " ");
  }

  @Test
  public void getValueEqualsEmptyString() {
    Assert.assertEquals("", new Statement().getValue());
  }

  @Test
  public void getValueNonEmptyString() {
    Assert.assertEquals("14", new Statement().create(Statement.LESS_THAN, "test.value", Statement.OR, "14").getValue());
  }

  @Test
  public void notEqualsCreate() {
    Assert.assertNotEquals(new Statement().create(Statement.GREATER_EQUAL_THAN, "test.value", Statement.NOT, "10"),
            new Statement().create(Statement.LESS_THAN, "test.value", Statement.NOT, "10"));
    Assert.assertNotEquals(new Statement().create(Statement.GREATER_EQUAL_THAN, "test.value", Statement.NOT, "10"),
            new Statement().create(Statement.GREATER_EQUAL_THAN, "foobar.value", Statement.NOT, "10"));
    Assert.assertNotEquals(new Statement().create(Statement.GREATER_EQUAL_THAN, "test.value", Statement.NOT, "10"),
            new Statement().create(Statement.GREATER_EQUAL_THAN, "test.value", Statement.OR, "10"));
    Assert.assertNotEquals(new Statement().create(Statement.GREATER_EQUAL_THAN, "test.value", Statement.NOT, "10"),
            new Statement().create(Statement.GREATER_EQUAL_THAN, "test.value", Statement.NOT, "20"));
    Assert.assertNotEquals(new Statement().create(Statement.GREATER_EQUAL_THAN, "test.value", Statement.NOT, "10"), new Statement());
  }

  @Test
  public void equalsTrueCreate() {
    Assert.assertEquals(new Statement().create(Statement.GREATER_EQUAL_THAN, "test.value", Statement.EQUALS, "10"),
                        new Statement().create(Statement.GREATER_EQUAL_THAN, "test.value", Statement.EQUALS, "10"));
  }

  @Test
  public void hashCodeEquals() {
    Assert.assertEquals(1302858907, new Statement().create(Statement.GREATER_EQUAL_THAN, "test.value",
            Statement.EQUALS, "10").hashCode());
  }

  @Test
  public void statementToStringNull() {
    Assert.assertEquals("null null null", new Statement().toString());
  }

  @Test
  public void statementToStringNotNull() {
    Assert.assertEquals("test.value OR 14", new Statement().create(Statement.LESS_THAN, "test.value", Statement.OR, "14").toString());
  }

}
