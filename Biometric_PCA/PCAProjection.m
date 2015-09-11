function [O, Ao] = PCAProjection(U, A)
    %Start PCA Projection; 
    %O (k x M) = Omega = PCA feature
    O = U' * A;
    %Ao (R x M) = Face Image from PCA feature
    Ao = U * O;
end