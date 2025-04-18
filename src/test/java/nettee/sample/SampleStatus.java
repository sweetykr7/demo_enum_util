package nettee.sample;

import nettee.enums.EnumUtil;
import nettee.sample.builder.TypeSafeMarkers;
import nettee.sample.StatusParameters.GeneralPurposeFeatures;
import java.util.HashSet;
import java.util.Set;

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
//    ,TEST(0)


    private final int code;

    //        for (var item : SampleStatus.values()) {
    //            System.out.println(item + ": " + item.getCode());
    //        }

    static{
//        Set<Integer> codes = new HashSet<>();
//        for (var item : SampleStatus.values()){
//            assert codes.add(item.code) : "duplicate code: " + item.code;
//        }
        assert EnumUtil.isUniqueAllOf(SampleStatus.class, SampleStatus::getCode)
                : "duplicate code";

    }
//    SampleStatus(int code) {
//        this.code = code;
//    }

//    SampleStatus(boolean canRead, int classifyingBits, int detailsBits) {
//        // 유효성 생략
//        // readable flag bit : 1
//        // classifying bits : 16
//        // detailsBits : 15
//        this.code = (canRead ? 1 << 31 : 0)
//                | (classifyingBits << 15)
//                | detailsBits;
//    }

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

//    public static final class SampleStatusParameters<INPUTTED_CLASSIFYING_BITS, INPUTTED_DETAIL> {
//        boolean canRead;
//        int classifyingBits;
//        int detailBits;
//
//        private SampleStatusParameters() {}
//        public SampleStatusParameters<INPUTTED_CLASSIFYING_BITS, INPUTTED_DETAIL> canRead(boolean canRead) {
//            this.canRead = canRead;
//            return this;
//        }
//
//        public SampleStatusParameters<TypeSafeMarkers.Present, INPUTTED_DETAIL> classifyingBits(int classifyingBits) {
//            this.classifyingBits = classifyingBits;
//            @SuppressWarnings("unchecked")
//            var instance = (SampleStatusParameters<TypeSafeMarkers.Present, INPUTTED_DETAIL>) this;
//            return instance;
//        }
//
//        public SampleStatusParameters<INPUTTED_CLASSIFYING_BITS, TypeSafeMarkers.Present> detailBits(int detailBits) {
//            this.detailBits = detailBits;
//            @SuppressWarnings("unchecked")
//            var instance = (SampleStatusParameters<INPUTTED_CLASSIFYING_BITS, TypeSafeMarkers.Present>) this;
//            return instance;
//        }
//
//
//
//
//
//
//    }
}