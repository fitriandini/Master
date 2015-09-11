function A = LoadImageData(F, p, startIm, n)
    %A (R x M) = Matrix of training Data 
    indx = 1;
    for i = 1:p
        for j=startIm: startIm+n-1
            %convert I into data type double with range [0 1]
            I = double(F.FaceData(i,j).Image)/255; 
            %training matrix of I
            A(:,indx) = reshape(I, size(I,1)*size(I,2),1);
            indx = indx+1;
        end
    end
end