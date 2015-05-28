import java.lang.Math;
/**
 * Write a description of class SymmetricMatrix here.
 * 
 * @author (Jung Hwang) 
 * @version (5/27/15)
 */
public class SquareMatrix extends Matrix//nxn matrix
{
    private double[][] mat;
    //row == col
    private int row;
    private int col;
    public SquareMatrix(int r)//you only need one variable for the constructor, since it's a square matrix!
    {
        super(r,r);//creating a matrix such that row=col=r
        row=r;
        col=r;
        this.mat = new double[r][r];
    }
    private SquareMatrix subMatrix(int y){//gets the "submatrix" of (0,y), used for the recursive call of determinant()
        SquareMatrix a = new SquareMatrix (row-1);
        int ctr=0;
        for(int n=1;n<row;n++)
        while(ctr<col-1){
            a.set(n,ctr,this.mat[n][ctr]);
            ctr++;
            if(ctr==y)
                ctr++;
        }
        return a;
    }
    public double determinant(){//recursive call of determinant of a matrix using linear algebra!!
        if(row==1)//base case. 1x1 matrix's determinant is its one and only entry at (0,0)
            return this.mat[0][0];
        else{
            double a=0//sum of the determinants*value*(positie/negative)
            for(int ctr=0;ctr<row;ctr++)
                a+=Math.pow(-1,ctr)*this.get(0,ctr)*this.subMatrix(ctr).determinant();//by the definition of matrix, this holds true.
            return a;
        }
    }
}
