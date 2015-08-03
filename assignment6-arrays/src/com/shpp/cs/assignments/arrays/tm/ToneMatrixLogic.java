package com.shpp.cs.assignments.arrays.tm;

public class ToneMatrixLogic {
    /**
     * Given the contents of the tone matrix, returns a string of notes that should be played
     * to represent that matrix.
     *
     * @param toneMatrix The contents of the tone matrix.
     * @param column     The column number that is currently being played.
     * @param samples    The sound samples associated with each row.
     * @return A sound sample corresponding to all notes currently being played.
     */
    public static double[] matrixToMusic(boolean[][] toneMatrix, int column, double[][] samples) {

        double[][] buffer = new double[samples.length][samples[0].length];


        for (int row = 0; row < toneMatrix.length; row++){
            if (toneMatrix[row][column]){
                buffer[row] = samples[row];

            }
        }
        double[] result = sumRows(buffer);
        result = normalizeSound(result);

        return result;
    }

    /* Converts two- dimensional array to one-dimensional by summing it all lines
    * */
    private static double[] sumRows(double[][] buffer) {

        double[] result = new double[buffer[0].length];

        for (int row = 0; row < buffer.length; row++){
            for (int col = 0; col < buffer[row].length; col++){
                result[col] += buffer[row][col];
            }
        }
        return result;
    }

    /* This method normalize the sound array by setting it maximum values
    * to (1; -1)view and proportionally reduces the other elements
    * */
    private static double[] normalizeSound(double[] sounds) {

        double[] result = new double[sounds.length];
        double max = maxElement(sounds);

        if (max == 0){
            return result;
        }
        else {
            for (int i = 0; i < sounds.length; i++){
                result[i] = sounds[i] / max;
            }
        }
        return result;
    }

    /* Return the absolute value of the maximum element of received array
    * */
    private static double maxElement(double[] sounds) {
        double result = 0;

        for (int i = 0; i < sounds.length; i++){
            double temp = sounds[i];
            if (temp < 0){
                temp *= -1;
            }
            if (temp > result){
                result = temp;
            }
        }
        return result;
    }
}
