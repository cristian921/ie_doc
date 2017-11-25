package matrix;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class testMatrix
{
	public static int N = 8;

	public static void printMatrix(ArrayList<ArrayList<Integer>> matrix)
	{
		System.out.println();
		for (int i = 0; i < matrix.size(); i++)
		{
			for (int j = 0; j < matrix.get(i).size(); j++)
				System.out.print(matrix.get(i).get(j) + "  ");
			System.out.println();
		}
	}

	public static void printMatrix(int[][] matrix, int n, int m)
	{
		System.out.println();
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < m; j++)
				System.out.print(matrix[i][j] + "  ");
			System.out.println();
		}
	}

	public static ArrayList<ArrayList<Integer>> createMatix(int n)
	{
		ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>(n);
		if (n != 0)
		{
			Random randomGenerator = new Random(System.nanoTime());

			for (int i = 0; i < n; i++)
			{
				matrix.add(new ArrayList<Integer>(n));
				for (int j = 0; j < n; j++)
					matrix.get(i).add(randomGenerator.nextInt(100) + 1);
			}
		}
		else
			System.out.print("ERRORE DIMENSIONE MATRICE - Non puo' avere dimensione sulle righe e/o colonne!\n");
		return matrix;
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		int rows = N;
		if(args.length == 1)
			rows = Integer.valueOf(args[0]).intValue();

		ArrayList<ArrayList<Integer>> A = createMatix(rows);
		ArrayList<ArrayList<Integer>> B = createMatix(rows);

		MatrixMultiplication matrix = new MatrixMultiplication();
		//Abilito la ricorsione su strassen solo 1 volta, imponendo leafsize=rows/2
		matrix.setLeafSize(rows/2);

		long startTime = System.nanoTime();
		int[][] c = matrix.strassen(A, B);
		long elapsedTime = System.nanoTime() - startTime;

		//Valutazione dei tempi di esecuzione e scrittura su file
		String path = "tempi(" + rows + "," + rows + ")x(" + rows + "," + rows + ").txt";
		try {
			File file = new File(path);
	        FileWriter fw = new FileWriter(file, true);
	        BufferedWriter bw = new BufferedWriter(fw);
	        bw.write(new DecimalFormat("######.########").format(((double)elapsedTime / 1000000000)) + "\n");
	        bw.flush();
	        bw.close();
	    }
	    catch(IOException e) {
	        e.printStackTrace();
	    }
	}
}
