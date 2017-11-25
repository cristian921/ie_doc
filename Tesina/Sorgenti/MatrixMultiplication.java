package matrix;
import java.util.ArrayList;

public class MatrixMultiplication {
    private static int LEAF_SIZE = 2;

    public void setLeafSize(int n)
    {
    	LEAF_SIZE = n;
    }

    public int[][] ikjAlgorithm(ArrayList<ArrayList<Integer>> A, ArrayList<ArrayList<Integer>> B) {
        int n = A.size();

        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] += A.get(i).get(k) * B.get(k).get(j);
                }
            }
        }
        return C;
    }

    public static int[][] ikjAlgorithm(int[][] A, int[][] B) {
        int n = A.length;

        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                for (int j = 0; j < n; j++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    private static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    private static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    private static int nextPowerOfTwo(int n) {
        int log2 = (int) Math.ceil(Math.log(n) / Math.log(2));
        return (int) Math.pow(2, log2);
    }

    public static int[][] strassen(ArrayList<ArrayList<Integer>> A,
            ArrayList<ArrayList<Integer>> B) {

    	int n = A.size();
        int m = nextPowerOfTwo(n);
        int[][] APrep = new int[m][m];
        int[][] BPrep = new int[m][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                APrep[i][j] = A.get(i).get(j);
                BPrep[i][j] = B.get(i).get(j);
            }
        }

        int[][] CPrep = strassenR(APrep, BPrep);
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = CPrep[i][j];
            }
        }
        return C;
    }

    private static int[][] strassenR(int[][] A, int[][] B) {
        int n = A.length;

        if (n <= LEAF_SIZE)
            return ikjAlgorithm(A, B);
        else
        {
        	int newSize = n/2;
            //Inizializzazione sotto-matrici
            int[][] a11 = getSubMatrix(A, newSize, 0);
            int[][] a12 = getSubMatrix(A, newSize, 1);
            int[][] a21 = getSubMatrix(A, newSize, 2);
            int[][] a22 = getSubMatrix(A, newSize, 3);

            int[][] b11 = getSubMatrix(B, newSize, 0);
            int[][] b12 = getSubMatrix(B, newSize, 1);
            int[][] b21 = getSubMatrix(B, newSize, 2);
            int[][] b22 = getSubMatrix(B, newSize, 3);

            int[][] aResult = new int[newSize][newSize];
            int[][] bResult = new int[newSize][newSize];

            //Calcolo le funzioni m1-m7
            aResult = add(a11, a22);
            bResult = add(b11, b22);
            int[][] m1 = strassenR(aResult, bResult);
            // m1 = (a11+a22) * (b11+b22)

            aResult = add(a21, a22); // a21 + a22
            int[][] m2 = strassenR(aResult, b11); // m2 = (a21+a22) * (b11)

            bResult = subtract(b12, b22); // b12 - b22
            int[][] m3 = strassenR(a11, bResult);
            // m3 = (a11) * (b12 - b22)

            bResult = subtract(b21, b11); // b21 - b11
            int[][] m4 = strassenR(a22, bResult);
            // m4 = (a22) * (b21 - b11)

            aResult = add(a11, a12); // a11 + a12
            int[][] p5 = strassenR(aResult, b22);
            // p5 = (a11+a12) * (b22)

            aResult = subtract(a21, a11); // a21 - a11
            bResult = add(b11, b12); // b11 + b12
            int[][] m6 = strassenR(aResult, bResult);
            // m6 = (a21-a11) * (b11+b12)

            aResult = subtract(a12, a22); // a12 - a22
            bResult = add(b21, b22); // b21 + b22
            int[][] m7 = strassenR(aResult, bResult);
            // m7 = (a12-a22) * (b21+b22)

            //Calcolo C11
            aResult = add(m1, m4);
            bResult = add(aResult, m7);
            int[][] c11 = subtract(bResult, p5);

            //Calcolo C12
            int[][] c12 = add(m3, p5);

            //Calcolo C21
            int[][] c21 = add(m2, m4);

            //Calcolo C22
            aResult = add(m1, m3);
            bResult = add(aResult, m6);
            int[][] c22 = subtract(bResult, m2);

            int[][] C = new int[n][n];
            for (int i = 0; i < newSize; i++) {
                for (int j = 0; j < newSize; j++) {
                    C[i][j] = c11[i][j];
                    C[i][j + newSize] = c12[i][j];
                    C[i + newSize][j] = c21[i][j];
                    C[i + newSize][j + newSize] = c22[i][j];
                }
            }
            return C;
        }
    }

    private static int[][] getSubMatrix(int[][] matrix, int newSize, int number)
	{
		int[][] submatrix = new int[newSize][newSize];
		//Controllo che il number inserito sia compreso nell'intervallo accettabile (0-3)
		if(number < 4 && number >= 0)
		{
			int index_row = (int)(number/2)*newSize;
			int index_col = (number%2)*newSize;
			for(int i=0; i<newSize; i++)
				for(int j=0; j<newSize; j++)
					submatrix[i][j] = matrix[i+index_row][j+index_col];
		}
		else
			System.out.print("\nERRORE NUMBER - 'number' eccede l'intervallo (0-3)\n");

		return submatrix;
	}
}
