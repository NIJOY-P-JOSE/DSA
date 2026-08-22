class Solution:
    def minFallingPathSum(self, matrix: List[List[int]]) -> int:
        r = len(matrix)

        if r == 1:
            return matrix[0][0]

        for i in range(1, r):
            for j in range(r):

                if j == 0:
                    matrix[i][j] += min(
                        matrix[i-1][j],
                        matrix[i-1][j+1]
                    )

                elif j == r-1:
                    matrix[i][j] += min(
                        matrix[i-1][j],
                        matrix[i-1][j-1]
                    )

                else:
                    matrix[i][j] += min(
                        matrix[i-1][j-1],
                        matrix[i-1][j],
                        matrix[i-1][j+1]
                    )

        return min(matrix[r-1])
