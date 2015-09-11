%F load image File
%p = number of person in FaceData
%n = number of image per person used from FaceData
%startIm = start of Image index --
%--startIm = 1 for Training Set; and startIm = 6 for Test Set
%k [k1 k2 k3... kn] = array of PCA features size 
function [A, U, D, O, Ao] = Number1(F, p, n, imSize, k)
    %Plot the first 20 Eigenfaces -- Training Data
    startIm = 1; %Training Set
    %k = 20; %to compute the first 20 eigenfaces
    
    %Start PCA Training 
    %Form a training Matrix A (R x M)
    A = LoadImageData(F, p, startIm, n);
    %Compute the C (R x R) = Covariance Matrix of A-transpose
    %we transpose A; so that every different image in A is stored in rows
    %(previously different image is store in columns)
    C  = cov(A');  
    
    %PCA Projection
    [U ,D] = EigenFaces(C);
    
    O = zeros(size(A));
    for i=1:size(k,2)
        [O(1:k(i),:,i), Ao(:,:,i)] = PCAProjection(U(:, 1:k(i)), A); 
    end
    PlotImage(U, [4 5], imSize, '20 Eigenfaces of PCA training set');
    
end