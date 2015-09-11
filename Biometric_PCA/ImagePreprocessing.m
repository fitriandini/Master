%Pre-processing for non-face image; 
%in this assignment, we use the image of red-panda 
function I = ImagePreprocessing(imgFile, left_eye, right_eye, oriImSize, imSize)
    %read image from file
    I = imread(imgFile);
    %convert image into grayscale
    I = rgb2gray(I);
    %rotate the image so that the eyes lies parallel with horizonatal line
    y = left_eye(1) - right_eye(1);
    x = left_eye(2) - right_eye(2);
    t = atan2(y,x);
    I = imrotate(I, -t, 'bilinear', 'crop');
    
    %crop image into certain pixel 
    ytop = min(left_eye(1), right_eye(1))+y/2- (oriImSize(2)/2);
    xleft = min(left_eye(2), right_eye(2)) + abs(x)/2 - (oriImSize(1)/2);
    I = imcrop(I, [xleft ytop oriImSize]);
    
    %Resize theimage into the sample pixel
    I = imresize(I, imSize);
    
    %transpose into Rx1 matrix
    %convert I into data type double with range [0 1]
    I = double(I)/255; 
    %training matrix of I
    I = reshape(I, imSize(1)*imSize(2),1);
end