public class Radix {

  public static int nth(int n, int col) {
    n = (int)(n / Math.pow(10, col));
    return n % 10;
  }

  public static int length(int n) {
   int length = 0;
   if(n == 0) {
     length = 1;
   }
   else {
     n = Math.abs(n);
     length = (int)Math.log10(n);
     length++;
   }
   return length;
 }

  public static void merge(SortableLinkedList original, SortableLinkedList[] buckets) {
    for(int i = 0; i < buckets.length; i++) {
      original.extend( buckets[i] );
    }
  }

  public static void radixSortSimple(SortableLinkedList data){
    SortableLinkedList[] buckets = new SortableLinkedList[10];
    int max = 1;
    for(int i = 0; i < data.size(); i++){
      if(length(data.get(i)) > 1){
        max = length(data.get(i));
      }
    }
    for(int i = 0; i < 10; i++){
      buckets[i] = new SortableLinkedList();
    }

    for(int pos = 0; pos < max; pos++){
      while (data.size() > 0) {
        int rem = data.remove(0);
        buckets[nth(rem,pos)].add(rem);
      }
      merge(data, buckets);
    }
  }

  public static void radixSort (SortableLinkedList data) {
    SortableLinkedList posVal = new SortableLinkedList();
    SortableLinkedList negVal = new SortableLinkedList();
    for (int i = 0; i < data.size(); i++) {
      while (data.size() > 0) {
        int rem = data.remove(0);
        if (rem >= 0) {
          posVal.add(rem);
        }
        else {
          negVal.add(rem * -1);
        }
      }
    }
      radixSortSimple(posVal);
      radixSortSimple(negVal);

      //negative currently in reverse
      // must merge from the back
      for (int n = negVal.size() - 1; n >= 0; n--) {
        int actNeg = negVal.get(n);
        data.add(actNeg * -1);
      }
      data.extend(posVal);
  }
}
