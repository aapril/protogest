package com.protogest.service;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class Constants {
    public static final Map<String, String> CourSuperieurFieldDescriptions = ImmutableMap.<String, String>builder()
            .put("8", "Date limite du dépôt: Moyens déclinatoires, renvoi au tribunal compétent ou rejet (art. 167 C.p.c.)")
            .put("9", "Date limite du dépôt: Moyens déclinatoires, autre (avec référence à l’article C.p.c.)")
            .put("12", "Date limite du dépôt: Moyens d’irrecevabilité, en rejet (art. 168 C.p.c) (Case 12)")
            .put("15", "Date limite du dépôt: Autres moyens préliminaires, précisions(art. 169 C.p.c.) (Case 15)")
            .put("16", "Date limite du dépôt: Autres moyens préliminaires, communication de documents (art. 169 C.p.c.)")
            .put("17", "Date limite du dépôt: Autres moyens préliminaires, radiation d’allégations non pertinentes (art. 169 C.p.c.)")
            .put("18", "Date limite du dépôt: Autres moyens préliminaires, requête pour cautionnement (art. 492 C.p.c.)")
            .put("19", "Date limite du dépôt: Autres moyens préliminaires, autre ")
            .put("22", "Date limite du dépôt: Demande en vertu de l’article 51 C.p.c.")
            .put("25", "Date limite du dépôt: Demande pour mesures de sauvegarde")
            .put("28", "Date limite du dépôt: Autres incidents procéduraux, modification d’un acte de procédure")
            .put("29", "Date limite du dépôt: Autres incidents procéduraux, décisions sur un point de dro")
            .put("30", "Date limite du dépôt: Autres incidents procéduraux, déclaration d’inhabilité")
            .put("31", "Date limite du dépôt: Autres incidents procéduraux, autre")
            .put("35", "Date limite du dépôt: Date limite pour le dépôt de la demande reconventionnelle")
            .put("36", "Date limite du dépôt: Date limite pour le dépôt de la demande reconventionnelle")
            .put("39", "Date limite du dépôt: Date limite pour l’intervention ou la mise en cause d’un tiers")
            .put("40", "Date limite du dépôt: Date limite pour le dépôt de l’expertise commune")
            .put("41", "Date limite du dépôt: Date limite pour le dépôt de(s) expertise(s) en demande")
            .put("43", "Date limite du dépôt: Date limite pour le dépôt de(s) expertise(s) par le tiers ou le mis en cause")
            .put("52", "Date limite du dépôt: Interrogatoires, date limite pour le dépôt des transcriptions en demande (art. 227 C.p.c.)")
            .put("53", "Date limite du dépôt: Interrogatoires, date limite pour le dépôt des transcriptions en défense (art. 227 C.p.c.)")
            .put("54", "Date limite du dépôt: Interrogatoires, date limite pour le dépôt des transcriptions par le mis en cause (art. 227 C.p.c.)")
            .put("55", "Date limite du dépôt: Interrogatoires, date limite pour soumettre les objections énoncées à l'article 228 al. 2 C.p.c. soulevées lors des interrogatoires préalables en demande")
            .put("56", "Date limite du dépôt: Interrogatoires, date limite pour soumettre les objections énoncées à l'article 228 al. 2 C.p.c. soulevées lors des interrogatoires préalables en défense")
            .put("57", "Date limite du dépôt: Interrogatoires, date limite pour la communication de tous les engagements souscrits lors des interrogatoires préalables en demande")
            .put("58", "Date limite du dépôt: Interrogatoires, date limite pour la communication de tous les engagements souscrits lors des interrogatoires préalables en défense")
            .put("59", "Date limite du dépôt: Dépôt des pièces en demande ")
            .put("60", "Date limite du dépôt: Dépôt des pièces en défense ")
            .put("61", "Date limite du dépôt: Dépôt des pièces par le tiers, le mis en cause ou l’intervenant")
            .put("64", "Date limite du dépôt: Dépôt des déclarations écrites en demande")
            .put("65", "Date limite du dépôt: Dépôt des déclarations écrites pour en défense")
            .build();

    public enum CourSuperieurDuQuebecDateFields {
        c8, c9, c12, c16, c17, c18, c19, c22, c25, c28, c29, c30, c31, c35, c36, c39, c40, c41, c42, c43, c52, c53, c54, c55, c56, c57, c58, c59, c60, c61, c64, c65
    }


}
