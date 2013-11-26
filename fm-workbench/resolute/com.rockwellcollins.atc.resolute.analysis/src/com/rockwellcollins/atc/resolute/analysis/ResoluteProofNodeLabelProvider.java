package com.rockwellcollins.atc.resolute.analysis;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;

import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;

public class ResoluteProofNodeLabelProvider implements ILabelProvider {
    private static final Image VALID_IMAGE = loadImage("/icons/valid.png");
    private static final Image INVALID_IMAGE = loadImage("/icons/invalid.png");
    private static final Image ERROR_IMAGE = loadImage("/icons/error.png");

    private static Image loadImage(String filename) {
        try (InputStream stream = ResoluteProofNodeLabelProvider.class
                .getResourceAsStream(filename)) {
            return new Image(null, new ImageData(stream));
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public Image getImage(Object element) {
        if (element instanceof ResoluteProofNode) {
            ResoluteProofNode node = (ResoluteProofNode) element;
            ResoluteValue value = node.getRetVal();

            if (value.isBool()) {
                if (value.getBool()) {
                    return VALID_IMAGE;
                } else {
                    return INVALID_IMAGE;
                }
            }

            if (value.isFail()) {
                return ERROR_IMAGE;
            }

            return null;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getText(Object element) {
        if (element instanceof ResoluteProofNode) {
            ResoluteProofNode node = (ResoluteProofNode) element;
            String text = node.getExprStr();
            if (text.contains("\n")) {
                return text.substring(0, text.indexOf("\n")) + " ...";
            } else {
                return text;
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void addListener(ILabelProviderListener listener) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    @Override
    public void removeListener(ILabelProviderListener listener) {
    }
}
