function [U ,D] = EigenFaces(C, k)
    %Compute the U = eigenvector; size U is R x k
    %D is the eigenvalues matrix
    [U, D] = eig(C);
    %flip the matrix so that the largest eigenvalue is on the leftmost column of matrix
    U = fliplr(U);
    D = fliplr(D);
end