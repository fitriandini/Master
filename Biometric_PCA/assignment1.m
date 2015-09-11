%Fitria Nur Andini -- Andy Cui.J%
%TUe CSE - IST Master 2013%
%imgFile = 'FaceData.mat';
%startIm = start of Image index --
%--startIm = 1 for Training Set; and startIm = 6 for Test Set
%k [k1 k2 k3... kn] = array of PCA features size 

%size M = p x n = 40 x 5 = 200
%size R = P x Q
function assignment1
    imgFile = 'FaceData.mat';
    F = load(imgFile);
    p = size(F.FaceData,1); %get number of person in FaceData
    n = 5; %number of image per person
    %get Image pixel (imageSize) [P Q]
    imSize = size(F.FaceData(1,1).Image); 
    k_test1 = [2 5 10 20 40 60 100 150 200 400 1000 2000];
    %Assignment #1
    [A_train, U, D, O_train, Ao_train] = Number1(F, p, n, imSize, k_test1);
    
    %Assignment #2A
    index_p = 1; %can be change into whatever index of image that want to show
    startIm = 6;
    %Form a training Matrix A (R x M)
    A_test = LoadImageData(F, p, startIm, n);
    [Ot, Ao_test] = Number2(A_test, U, imSize, index_p, k_test1);
   
    %Assignment #2B
    index_p = 1; %only 1 image of non-face
    imgFile = 'red-panda.jpeg';
    %left eye [y x] and right eye[y x] coordinate for "red-panda.jpeg"
    %these coordinates was manually taken using matlab imshow - and click on Data Cursor tool
    left_eye = [205 249]; 
    right_eye = [200 384];
    oriImSize = [300 360];
    %Load non-face Image Data
    A_test_nf = ImagePreprocessing(imgFile, left_eye, right_eye, oriImSize, imSize);
    [Ot_nf, Ao_test_nf] = Number2(A_test_nf, U, imSize, index_p, k_test1);
   
    %Assignment #3
    Number3(D, k_test1, O_train, Ot, n);
    
    
end

