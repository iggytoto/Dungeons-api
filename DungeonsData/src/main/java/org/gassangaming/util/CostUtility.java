package org.gassangaming.util;

import org.gassangaming.model.Account;
import org.gassangaming.model.Valuable;

public class CostUtility {

    public static boolean isAccountHasEnoughFor(Account a, Valuable v) {
        return a.getGoldAmount() >= v.getGoldCost();
    }

    public static void reduceAccountOn(Account a, Valuable v) throws IllegalStateException {
        if (isAccountHasEnoughFor(a, v)) {
            a.setGoldAmount(a.getGoldAmount() - v.getGoldCost());
        } else {
            throw new IllegalStateException("account has not enough to buy this.");
        }
    }
}
