
/**
 * Write a description of class Matrix here.
 * 
 * @author (Jung Hwang) 
 * @version (5/27/15)
 */
public class Matrix
{

    private int row;//number of rows
    private int col;//number of columns
    private double[][] mat;//matrix itself
    public Matrix(int x, int y){//constructor of the matrix
        this.row=x;
        this.col=y;
        this.mat = new double[x][y];//declaring the size of the 2d array
    }
    //precondition : r and c are within the bounds
    public void set(int r, int c, double z){
        this.mat[r][c] = z;//sets the value of the matrix at (r,c)
    }
    //precondition : r and c are within the bounds
    public double get(int r, int c){
        return this.mat[r][c];//returns the element at (r,c)
    }
    //precondition : a has same dimension as this
    public Matrix add(Matrix a){
        Matrix b = new Matrix (row,col);//initializing and declaring a Matrix
        for(int m=0;m<a.row;m++)
            for(int n=0;n<a.col;n++)
                b.set(m,n,this.get(m,n)+a.get(m,n));//setting each of the value as sum of two matrices
        return b;
    }
    public Matrix transpose(){//returns the transpose of the matrix
        Matrix a = new Matrix (col,row);//reverses the dimension of the matrix
        for(int m=0;m<a.row;m++)
            for(int n=0;n<a.col;n++)
                a.set(n,m,this.get(m,n));
        return a;
    }
    public Matrix multiply ( double k){//multiplying a scalar
        Matrix a = new Matrix(row,col);
        for(int m=0;m<this.row;m++)
            for(int n=0;n<this.col;n++)
                a.set(m,n,k*this.get(m,n));//set each value to equal k*(m,n)
        return a;
    }
    //precondition: number of columns of this is equal to number of rows of a
    public Matrix multiply (Matrix a){//multiplying this by a
        Matrix b = new Matrix(row,a.getMat()[0].length);//initializing returning matrix
        double sum = 0;//sum of the products along the row of this and column of a
        for(int m=0;m<this.row;m++){
            for(int n=0;n<a.getMat()[0].length;n++){//calling each entires of b
                for(int x=0;x<this.col;x++)//going along the row of this and column of a at the same time
                        sum+=this.get(m,x)*a.get(x,n);//computing the sum of values of the products
                b.set(m,n,sum);//setting b(m,n) equal to the sum, just like the definition of matrix multiplication
                sum=0;}}//resetting the value of sum
        return b;
    }
    private Matrix getRow(int r){//returning the matrix of a single row, used for method ref only
        Matrix a = new Matrix(1,col);
            a.mat[0] = this.mat[r];//making sure that the dimension of each side is equal to each other
            return a;
    }
    public double[][] getMat(){
        return this.mat;
    }
    public Matrix ref(){ // rows of 0 may appear in the middle
        Matrix a = this;
        for(int z=1;z<row;z++){//goes through each of the columns and subtracts each of the values via row operations
            for(int x=z-1;x<this.row;x++){//makes leading things into 1
                if(a.get(x,z)!=0)//making sure that the degenarate case 1/0 does not happen
                a.mat[x] = a.getRow(x).multiply(1/(a.get(x,z))).mat[0];//by dividing, the zth entry automatically becomes 1
            }
            for(int y=z;y<this.row;y++){//makes leading things into 0 by subtracting other rows
                if(a.get(y,z)!=0)//making sure that 0-1=-1 degenerate case does not happen
                a.mat[y]=a.getRow(y).add(a.getRow(z-1).multiply(-1)).mat[0];//by subtracting, the zth entry automatically becomes 0
            }
        }
        return a;
    }
}
