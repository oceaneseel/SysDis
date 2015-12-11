/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exception;

/**
 *
 * @author John
 */
public class notEnoughMoney extends Exception {

    /**
     * Creates a new instance of <code>notEnoughMoney</code> without detail
     * message.
     */
    public notEnoughMoney() {

    }

    /**
     * Constructs an instance of <code>notEnoughMoney</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public notEnoughMoney(String msg) {
        super(msg);
    }
}
