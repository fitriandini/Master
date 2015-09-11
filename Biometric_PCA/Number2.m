function [Ot, Ao_test]= Number2(A_test, Ut, imSize, index_p, k_test1)
    %Reconstruct sample face image from the test set using PCA feature k_test1
    Ot = zeros(size(A_test));
    for i=1:size(k_test1,2)
        %PCA Projection
        %get the K (k_test1(i)) largest Ut
        [Ot(1:k_test1(i),:,i), Ao_test(:,:,i)] = PCAProjection(Ut(:, 1:k_test1(i)), A_test);
    end
    
    %Plot Image
    %index of person that want to show
    figure;
    set(gcf,'numbertitle','off','name', 'Sample Face Image using PCA feature');
    index = 1;
    plotSize = [3 4];
    for i=1:plotSize(1)
        for j=1:plotSize(2)
            subplot(plotSize(1)+1, plotSize(2), index);
            %reshape Image in column index into imageSize
            imshow(reshape(Ao_test(:,index_p,index), imSize), []);
            title(k_test1(index));
            index = index + 1;
        end
    end
    subplot(plotSize(1)+1, plotSize(2), index);
    imshow(reshape(A_test(:,index_p), imSize), []);
    title('Original Image');
end