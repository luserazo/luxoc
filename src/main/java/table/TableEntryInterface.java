/*
 * File: TableEntryInterface.java
 *
 * Desc: describes how a Symbol Table Entry should behave.
 *
 */

package main.java.table;

/**
 * TableEntryInterface
 * @author Luis Serazo
 */
public interface TableEntryInterface {
    /* method stubs. */
    boolean isVariable();
    boolean isKeyword();
    boolean isProcedure();
    boolean isFunction();
    boolean isFunctionResult();
    boolean isParameter();
    boolean isArray();
    boolean isConstant();
    boolean isReserved();
}
