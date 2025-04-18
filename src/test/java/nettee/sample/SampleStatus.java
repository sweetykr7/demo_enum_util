package nettee.sample;

import nettee.enums.EnumUtil;
import nettee.sample.builder.TypeSafeMarkers;
import nettee.sample.StatusParameters.GeneralPurposeFeatures;

public enum SampleStatus {
    PENDING(StatusParameters.generate()
            .generalPurposeFeatures()
            .categoryBits(1)
            .instanceBits(0)),
    ACTIVE(StatusParameters.generate()
            .generalPurposeFeatures(GeneralPurposeFeatures.ALL)
            .categoryBits(2)
            .instanceBits(0)),
    REMOVED(StatusParameters.generate()
            .generalPurposeFeatures(GeneralPurposeFeatures.READ,
                                    GeneralPurposeFeatures.SUBITEM_READ
            )
            .categoryBits(0)
            .instanceBits(0));

    private final int code;

    static{
//        Set<Integer> codes = new HashSet<>();
//        for (var item : SampleStatus.values()){
//            assert codes.add(item.code) : "duplicate code: " + item.code;
//        }
        assert EnumUtil.isUniqueAllOf(SampleStatus.class, SampleStatus::getCode)
                : "duplicate code";

    }

    SampleStatus(StatusParameters<TypeSafeMarkers.Present, TypeSafeMarkers.Present> parameters) {
        this.code = (parameters.generalPurposeBits() << 24)
                | (parameters.systemInfoBits() << 16)
                | (parameters.categoryBits() << 8)
                | parameters.instanceBits();
    }

    public static SampleStatus valueOf(int code) {
        return switch (code) {
            case 0b0_000_0000__0000_0000__0000_0001__0000_0000 -> PENDING;
            case 0b0_110_1100__0000_0000__0000_0010__0000_0000 -> ACTIVE;
            case 0b0_100_1000__0000_0000__0000_0000__0000_0000 -> REMOVED;
            default -> null;
        };
    }

    public int getCode() {
        return code;
    }
}