import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Operations_employee {
	private Employee[] e1;
	private int len;
	private Scanner scn;
	private String filename;

	 public String getName( int i) {
		 return e1[i].getName();
		 }

	 public double getSalary( int i) {
		 return e1[i].getSalary();
		 }
	 public String getType( int i) {
		 return e1[i].getType();
		 }

	 public Operations_employee (String fn) {
		 this.filename=fn;
		 this.len = GetRowQtyInFile();
		e1 = new Employee[len] ;
		openfile();
		readfile();
	 }
	 public int GetRowQtyInFile() {
		 /*считает количество строк в файле*/
		 int lineNumber = 0;
	 try{
		 File myFile =new File(filename);
		FileReader fileReader = new FileReader(myFile);
		 LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
		 while (lineNumberReader.readLine() != null){
		 lineNumber++;
		 }
		 lineNumberReader.close();
		 }catch(IOException e){
		 e.printStackTrace();
		 }
	 return lineNumber;
	 }
	 public void openfile() {
		 /*открывает файл*/
		 try {
			 scn = new Scanner (new File(filename));
		 } catch(Exception e) {JOptionPane.showMessageDialog(null, "Файл не найден");}
		}
	 public void readfile() {
		 /*читает файл*/
		 String n; /*тут будет прочитано ниже ID - служащего*/
		 String type; /*тут будет прочитано тип зар. платы - служащего (оклад или повременщик)*/
		 String s; /*имя служащего*/
		 String sal; /*тут будет зар. плата в случае служащего с окладом или время в случае повременщика */
		    for (int row=0;row<len-1;row++ ) {
		    	n=scn.next();
		    	type=scn.next();
		    	s=scn.next();
		    	sal=scn.next();
	if (type.equals("t")) {e1[row] = new Timed(  Integer.parseInt(n) , s,  Float.parseFloat(sal));}
	if (type.equals("f")) { e1[row] = new FixedPriceEmployee(  Integer.parseInt(n) , s,  Float.parseFloat(sal));}
		 }
	 }
	 public void change_elements(int i, int j) {
		 /*меняет элементы массива местами*/
		 Employee e10;

		 	e10=e1[i];
		 	e1[i]=e1[j];
		 	e1[j]=e10;
	 }
	 public void view_all_elements() {
		 /*вывод элементов массива */
		 for (int i = 0; i <= len-1; i++) {
			 if (i<=len-2) {System.out.print(i) ;System.out.print(e1[i].getName()+" "+e1[i].getType()+" "+e1[i].getSalary());}
			  System.out.println();
			}
	 }
	 public void sort_elements() {
		 /*пузырьковая сортировка */
		int k=len-2;
		for (int j = 0; j <= len-2; j++) {
					 for (int i = 0; i <= k; i++) {
			 if (i+1<=len-2) {
			 if (e1[i].getSalary()<e1[i+1].getSalary()) {change_elements(i, i+1);}
			 }
			} k--;
		}
		/*сортировка по именам в алфавитном порядке в элементах с одинаковой зар. платой */
		k=len-2;
		for (int j = 0; j <= len-2; j++) {
			for ( int i = 0; i <= k; i++) {
			 if (i+1<=len-2) {
			 if ((e1[i].getSalary()==e1[i+1].getSalary()) && e1[i].getName().compareTo(e1[i+1].getName())   >0 ) {change_elements(i, i+1);}
			 }
			} k--;
		}
	 }
}
