package com.wanted.clone.oneport.payments.infrastructure.pg.toss;

import com.wanted.clone.oneport.payments.application.port.out.pg.PgWidget;

public class TossPgWidget extends PgWidget {
    @Override
    public String checkout() {
        return "toss/checkout";
    }

    @Override
    public String success() {
        return "toss/success";
    }

    @Override
    public String fail() {
        return "toss/fail";
    }
}
