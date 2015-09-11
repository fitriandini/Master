function PlotTable (tabelData, rank1, k)
    hfig = figure;
    set(gcf,'numbertitle','off','name', 'Identification Performance of PCA approach', 'Position', [20 20 500 400]) ;
    set(hfig, 'Position', [10 10 500 700]);
    subplot(2,1,1);
    plot(k, rank1);
    title('Rank 1 Recognition Rate');
    xlabel('Dimensionality');
    ylabel('Rank 1 Identification Accuracy');
    
    columnName = {'Number of Eigenfaces', 'Variance Explained', 'Rank-1 Recognition Rate'};
    data = tabelData;
    t = uitable('Data', data);
    set(t, 'ColumnName', columnName);
    set(t, 'ColumnWidth',{140});
    set(t, 'Position',[10 10 500 310]);
end