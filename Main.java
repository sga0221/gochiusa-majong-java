import java.util.Random;
import java.lang.System;

public class Main{
    static String hai[][] = {
	{":1man:",":2man:",":3man:",":4man:",":5man:",":6man:",":7man:",":8man:",":9man:"},
	{":1sou:",":2sou:",":3sou:",":4sou:",":5sou:",":6sou:",":7sou:",":8sou:",":9sou:"},
	{":1pin:",":2pin:",":3pin:",":4pin:",":5pin:",":6pin:",":7pin:",":8pin:",":9pin:"}
    };
    static String jihai[] = {":ton:",":nan:",":sha:",":pe:",":haku:",":hatsu:",":chun:"};

    static String bakaze[] = {"東","南","西"};
    static String jikaze[] = {"東","南","西","北"};
    
    static int hai_count[][] = new int[3][10];
 
    static int ji_count[] = new int[7];
    
    static long seed = System.currentTimeMillis();
    static Random r = new Random(seed);

    public static String bkaze(){
	return bakaze[r.nextInt(3)];
    }
    public static String jkaze(){
	return jikaze[r.nextInt(4)];
    }

    public static String dora(int i){
	int j;
	if(i == 3){
	    j= r.nextInt(7);
	    ji_count[j] =+ 1;
	    return jihai[j];
	}else{
	    j= r.nextInt(9);
	    hai_count[i][j] =+ 1;
	    return hai[i][j];
	}
    }
    //雀頭の設定
    public static String head(int i){
	int j;
	if(i == 3){
	    do{
		j = r.nextInt(7);
	    }while(ji_count[j] >= 3);
	    ji_count[j] =+ 2;
	    return (jihai[j] + jihai[j]);
	}else{
	    do{
		j = r.nextInt(9);
	    }while(hai_count[i][j] >=3);
	    hai_count[i][j] =+ 2;
	    return(hai[i][j] + hai[i][j]);
	}
    }
    
    //順子の設定
    public static String shuntsu(int i){
	int j;
	do{
	    j= r.nextInt(7) + 1;
	}while(hai_count[i][j-1] >= 4 || hai_count[i][j] >= 4 || hai_count[i][j+1] >= 4);
	hai_count[i][j-1]++;
	hai_count[i][j]++;
	hai_count[i][j+1]++;
	return (hai[i][j-1] + hai[i][j] + hai[i][j+1]);
    }

    //刻子の設定
    public static String kotsu(int i){
	int j;
	if(i == 3){ 
	    do{
		j= r.nextInt(7);
	    }while(ji_count[j] >= 2);
	    ji_count[j] =+ 3;
	    return (jihai[j] + jihai[j] + jihai[j]);
	}else{
	    do{
		j = r.nextInt(9);
	    }while(hai_count[i][j] >= 2);
	    hai_count[i][j] =+ 3;
	    return(hai[i][j] + hai[i][j] + hai[i][j]);
	}
    }
    //雀頭の生成
    public static void make_head(){
	int i = r.nextInt(4);
	System.out.print(head(i) + " ");
    }
    
    //その他の部分の生成
    public static void make_body(){
	int choice = r.nextInt(2);
	int n;
	switch (choice){
	case 0:
	    n = r.nextInt(3);
	    System.out.print(shuntsu(n) + " ");
	    break;
	case 1:
	    n = r.nextInt(4);
	    System.out.print(kotsu(n) + " ");
	    break;
	}
    }
	
    public static void main(String args[]){
	//hai_countの初期化
	for(int i = 0; i < 3;i++){
	    for(int j = 0; j < 10; j++){
		hai_count[i][j] = 0;
	    }
	}
	//ji_countの初期化
	for(int i = 0;i < 4;i++){
	    ji_count[i] = 0;
	}
	//kazeの出力
	System.out.println("場風: " + bkaze() + ", 自風: " + jkaze());
	
	//doraの出力
	int d = r.nextInt(4);
	System.out.println("ドラ: " + dora(d));

	//headの出力
	make_head();

	//bodyの出力
	//なきの設定.ないた部分を()で表現する
	int naki = r.nextInt(5);
	for(int k = 0 ; k < 4;k++){
	    if(k == naki)
		System.out.print("( ");
	    make_body();
	}
	
	//改行
	//ないた部分のかっこを閉じる
	if(naki != 4)
	    System.out.print(" )");

	
	System.out.println();
    }
}
