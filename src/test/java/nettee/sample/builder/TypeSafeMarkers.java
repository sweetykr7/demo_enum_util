package nettee.sample.builder;
import nettee.sample.builder.TypeSafeMarkers.Present;
import nettee.sample.builder.TypeSafeMarkers.Missing;

//public final class TypeSafeMarkers {
//    private TypeSafeMarkers() {}
//
//    public interface Present {}
//    public interface Missing {}
//}


public sealed interface TypeSafeMarkers permits Present, Missing {
    non-sealed interface Present extends TypeSafeMarkers {}
    non-sealed interface Missing extends TypeSafeMarkers {}
}