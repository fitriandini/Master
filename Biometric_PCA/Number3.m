%This function is used for calculating :
%Rank-1 Recognition Rate
%Total Variance (varK)
function [min_dist, indx, varK] = Number3(D, k, O_train, O_test, n)
    %reverse D
    D_rev = fliplr(D);
    %Sum of all eigenvalues (lambda)
    sigLR =  sum(diag(D_rev));
    
    %size of O_result is M x M
    O_result = zeros(size(O_train(:,:,1), 2));
    varK = zeros(size(k,2), 3);
    for i=1:size(k,2)
        %3A Euclid dist
        O_train1 = O_train(1:k(i),:,i);
        O_test1 = O_test(1:k(i),:,i);
        %get the minimum distance of per image of test from all the
        %training set
        O_result(:,:,i) = dist(O_train1', O_test1);
        
        [min_dist(:,:,i),indx(:,:,i)] = min(O_result(:,:,i));
        
        %determine the image is correct here
        %since per person has 5 image; and the total person tested is 40
        %then it has 40 class
        %for class of train was resulted from the minimum distance
        class_train(:,:,i) = ceil(indx(:,:,i)/n);
        %for class of test is decided from the index of the distance matrix
        %so we divide some 1 - 200 vector into 40 class
        class_test(:,:,i) = ceil([1:size(O_train(:,:,1), 2)]/n);
        %If the class test comparing to class train is zero, then it's
        %correct, and vice versa
        class_res(:,:,i) = class_test(:,:,i) - class_train(:,:,i);
        %If the comparion result between training class and test class is
        %not zero, then we convert it into 1; so that we can sum the
        %incorrect number of image recognition
        class_res(class_res~=0)=1;
        incorrect(i) = sum(class_res(:,:,i));
        correct(i) = size(class_res(:,:,i),2) - incorrect(i);
        rank1(i) = correct(i) / (correct(i) + incorrect(i));
        
        %3B 
        sigLK = sum(diag(D_rev(size(D_rev,2)-k(i)+1:size(D_rev,2), size(D_rev,2)-k(i)+1:size(D_rev,2))));
        totSigVar = sigLK / sigLR;
        
        %for displaying in the table
        varK(i,:) = [k(i) totSigVar rank1(i)];
    end
    PlotTable(varK, rank1, k);
end