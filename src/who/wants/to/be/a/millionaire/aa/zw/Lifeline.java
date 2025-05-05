/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package who.wants.to.be.a.millionaire.aa.zw;

/**
 *
 * @author Adhis
 */
public abstract class Lifeline {

    protected boolean used;

    public Lifeline() {
        this.used = false;
    }

    public boolean isUsed() {
        return used;
    }

    public abstract void use(Question question);
}
