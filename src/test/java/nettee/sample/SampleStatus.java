package nettee.sample;

import nettee.enums.EnumUtil;
import nettee.sample.builder.TypeSafeMarkers;

import java.util.HashSet;
import java.util.Set;

public enum SampleStatus {
    PENDING(new SampleStatusParameters()
            .classifyingBits(10)
            .canRead(false)),
    ACTIVE(new SampleStatusParameters()
            .classifyingBits(20)
            .canRead(false)),
    REMOVED(new SampleStatusParameters()
            .classifyingBits(0)
            .canRead(false))
//    ,TEST(0)
    ;

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

    SampleStatus(SampleStatusParameters<TypeSafeMarkers.Present, TypeSafeMarkers.Present> parameters) {
        this.code = (parameters.canRead ? 1 << 31 : 0)
                | (parameters.classifyingBits << 15)
                | parameters.detailBits;
    }

    public static SampleStatusParameters<TypeSafeMarkers.Missing, TypeSafeMarkers.Missing> generateParameters() {
        return new SampleStatusParameters<>();
    }

    public static SampleStatus valueOf(int code) {
        return switch (code) {
            case 10 -> PENDING;
            case 20 -> ACTIVE;
            case 0 -> REMOVED;
            default -> null;
        };
    }

    public int getCode() {
        return code;
    }

    public static final class SampleStatusParameters<INPUTTED_CLASSIFYING_BITS, INPUTTED_DETAIL> {
        boolean canRead;
        int classifyingBits;
        int detailBits;

        private SampleStatusParameters() {}
        public SampleStatusParameters<INPUTTED_CLASSIFYING_BITS, INPUTTED_DETAIL> canRead(boolean canRead) {
            this.canRead = canRead;
            return this;
        }

        public SampleStatusParameters<TypeSafeMarkers.Present, INPUTTED_DETAIL> classifyingBits(int classifyingBits) {
            this.classifyingBits = classifyingBits;
            @SuppressWarnings("unchecked")
            var instance = (SampleStatusParameters<TypeSafeMarkers.Present, INPUTTED_DETAIL>) this;
            return instance;
        }

        public SampleStatusParameters<INPUTTED_CLASSIFYING_BITS, TypeSafeMarkers.Present> detailBits(int detailBits) {
            this.detailBits = detailBits;
            @SuppressWarnings("unchecked")
            var instance = (SampleStatusParameters<INPUTTED_CLASSIFYING_BITS, TypeSafeMarkers.Present>) this;
            return instance;
        }






    }
}