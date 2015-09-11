%Plot One Array Image 
function PlotImage(Image, plotSize, imageSize, imageTitle)
    figure;
    set(gcf,'numbertitle','off','name', imageTitle) ;
    index = 1;
    for i=1:plotSize(1)
        for j=1:plotSize(2)
            subplot(plotSize(1), plotSize(2), index);
            %reshape Image in column index into imageSize
            imshow(reshape(Image(:,index), imageSize), []);
            title(index);
            index = index + 1;
        end
    end
end