package com.covidhelp.CovidHelp.constants;

public enum MedicalLeadVerificationType {
    VERIFY,
    UNDO_VERIFY,
    REJECT,
    UNDO_REJECT;

    public boolean isVerificationAction() {
        return VERIFY.equals(this) || UNDO_VERIFY.equals(this);
    }

    public boolean isRejectAction() {
        return REJECT.equals(this) || UNDO_REJECT.equals(this);
    }

    public boolean isVerified() {
        return MedicalLeadVerificationType.VERIFY.equals(this);
    }

    public boolean isRejected() {
        return MedicalLeadVerificationType.REJECT.equals(this);
    }
}
