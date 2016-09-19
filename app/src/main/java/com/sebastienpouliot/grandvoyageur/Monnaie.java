package com.sebastienpouliot.grandvoyageur;

import java.math.BigDecimal;

/**
 * Created by spouliot on 16-09-18.
 */
final public class Monnaie {
    private static final BigDecimal TAUX_USD_EN_CAD = new BigDecimal("1.3189");
    private static final BigDecimal TAUX_CAD_EN_USD = new BigDecimal("0.7582");

    private Monnaie(){}; // classe statique

    public static BigDecimal cadEnUsd(BigDecimal montantSource) {
        return montantSource.multiply(TAUX_CAD_EN_USD).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public static BigDecimal usdEnCad(BigDecimal montantSource) {
        return montantSource.multiply(TAUX_USD_EN_CAD).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
