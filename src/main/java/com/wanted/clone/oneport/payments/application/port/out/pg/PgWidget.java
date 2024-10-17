package com.wanted.clone.oneport.payments.application.port.out.pg;

import com.wanted.clone.oneport.payments.infrastructure.pg.toss.TossPgWidget;

public abstract class PgWidget {
    public abstract String checkout();

    public abstract String success();

    public abstract String fail();

    public static PgWidget of(String pgCorpName) {
        return switch (pgCorpName.toLowerCase()) {
            case "toss" -> new TossPgWidget();
            default -> throw new IllegalArgumentException("Invalid pgCorp name: " + pgCorpName);
        };
    }
}
