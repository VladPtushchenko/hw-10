import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class hw10{
    public static void main(String[] args) {
        String[] names = {"Ivan", "Pavel", "Denis", "Piter", "Elena", "Zuhel"};
        System.out.println(filterEvenNames(names));
        System.out.println(sortNames(names));
        String[] ints ={"1, 2, 0", "4, 5"};
        System.out.println(getInts(ints));
        long a = 25214903917L;
        long c = 11;
        long m = 2^48;
        long seed = 2;
        System.out.println(infinityRandomGen(a,c,m,seed).limit(10).collect(Collectors.toList()));

        String[] stream1 = {"11", "12", "13", "14"};
        String[] stream2 = {"21", "22", "23", "24", "25", "26"};
        System.out.println(zip(Arrays.stream(stream1),Arrays.stream(stream2)).collect(Collectors.toList()));
    }
    // task 1
    public static String filterEvenNames(String[] names){
           return IntStream.range(1, names.length+1)
                   .filter(i -> i%2==1)
                   .mapToObj(i -> ""+i+". "+names[i-1])
                   .collect(Collectors.joining(", "));
       }
    // task 2
    public static List<String> sortNames(String[] names){
        Stream<String> list = Arrays.stream(names);
        return list.map(String::toUpperCase).sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }
    // task 3
    public static String getInts(String[] array){
        return Arrays.stream(array)
                .flatMap(x -> {
                    String[] str = x.split(", ");
                    return Arrays.stream(str).map(s -> Integer.parseInt(s));
                })
                .sorted()
                .map(x -> Integer.toString(x))
                .collect(Collectors.joining(", "));

    }
    // task 4
    public static Stream<Long> infinityRandomGen(long a, long c, long m, long seed){
        return Stream.iterate(seed,n->1*(a*n + c) % m) ;
    }
    // task 5
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second){
        Iterator<T> iteratorF = first.iterator();
        Iterator<T> iteratorS = second.iterator();
        Iterable<Stream<T>> iterable = () -> new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iteratorF.hasNext() && iteratorS.hasNext();
            }

            @Override
            public Stream<T> next() {
                return Stream.of(iteratorF.next(), iteratorS.next());
            }
        };
        return StreamSupport.stream(iterable.spliterator(), false).flatMap(x->x);
    }
}
