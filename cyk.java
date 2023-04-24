import java.util.*;

public class cyk {
     static Map<String,List<String>> payane=new HashMap<>();
     static Map<String,List<String>> two_napayane=new HashMap<>();
     static int tool=0;
     static List<String> [][] jadval;
     static  List<String> chars=new ArrayList<>();

     public cyk(int n){
         tool=n;
     }

     public static void  set_payane_in_table(String [] array_reshte_vorudi){
         for (int z = 1; z <tool+1 ; z++) {
             int j=0;
             while ( j < chars.size()) {
                 if(payane.get(chars.get(j)).size()>0&&payane.get(chars.get(j)).contains(array_reshte_vorudi[z-1])){
                     if (jadval[z][z].contains("-")){
                         jadval[z][z].remove("-");}
                     jadval[z][z].add(chars.get(j));
                 }
                 j+=1;
             }

         }
     }

     public int count_of_payane(){
         int a=0;
         for (int i = 0; i < payane.size(); i++) {
             for (int j = 0; j < chars.size(); j++) {
                 a=payane.get(chars.get(j)).size()+a;
             }
         }
         return a;
     }

    public static String building_table(Map<String,List<String>> first_grammar,String [] array_reshte_vorudi){
        int c=2;
        int b=1;
        if(array_reshte_vorudi.length==1&&array_reshte_vorudi[0].equals("-")){
            if(payane.get("S").contains("-")){
                return "YES";
            }
            else {
                return "NO";
            }
        }
        jadval=new List[tool+1][tool+1];
        for (int i = 0; i < tool+1; i++) {
            for (int j = 0; j < tool+1; j++) {
                jadval[i][j]=new ArrayList<>();
                jadval[i][j].add("-");
            }
        }

        set_payane_in_table(array_reshte_vorudi);

        while (c<tool+1) {
            int h=1;
            int x=tool+2-c;
            for (h = 1; h <x ; h++) {
                int f=h-1;
                int p=f+c;
                int z=h;
                while (z < p) {
                    for (int m = 0; m < two_napayane.size(); m++) {
                        int r=0;
                        while (r < two_napayane.get(chars.get(m)).size()) {
                            String s=two_napayane.get(chars.get(m)).get(r);
                            String s1=s.substring(0,1);
                            String s2=s.substring(1);
                            if(jadval[h][z].contains(s1)&&jadval[z+1][p].contains(s2)){
                                if (jadval[h][p].contains("-")){
                                    jadval[h][p].remove("-");}
                                jadval[h][p].add(chars.get(m));
                            }
                            r++;
                        }
                    }
                    z++;
                }
            }
            c++;
        }
       if(jadval[1][tool].contains("S")){
        return "YES";}
       else
           return "NO";
  }
    public static void main(String[] args){
        Scanner scanner=new Scanner(System.in);
        String reshte=scanner.nextLine();
        String []a=reshte.split("");
        tool=reshte.length();
        int c=Integer.parseInt(scanner.nextLine());
        Map<String,List<String>> map=new HashMap<>();
        for (int i = 0; i < c; i++) {
            String s=scanner.nextLine();
            String name=s.substring(0,1);
            int j=s.indexOf('>');
            String h=s.substring(j+1);
            String [] array=h.split("\\|");
            List<String> p1=new ArrayList<>();
            List<String> np1=new ArrayList<>();
            for (int k = 0; k < array.length; k++) {
              if(array[k].length()==1){
                  p1.add(array[k]);
              }
              else {
                  np1.add(array[k]);
              }

            }
            payane.put(name,p1);
            two_napayane.put(name,np1);
            chars.add(name);
        }

        System.out.println(building_table(map,a));
    }
}
