package com.rockwellcollins.atc.resolute.analysis;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.RGB;

import com.rockwellcollins.atc.resolute.analysis.values.ResoluteValue;

public class ResoluteProofNodeLabelProvider implements ILabelProvider {
    private static final int IMAGE_SIZE = 16;
    private static final Image TRUE_IMAGE = new Image(null, new ImageData(IMAGE_SIZE, IMAGE_SIZE,
            1, new PaletteData(new RGB[] { new RGB(127, 255, 127) })));
    private static final Image FALSE_IMAGE = new Image(null, new ImageData(IMAGE_SIZE, IMAGE_SIZE,
            1, new PaletteData(new RGB[] { new RGB(255, 127, 127) })));
    private static final Image FAIL_IMAGE = new Image(null, new ImageData(IMAGE_SIZE, IMAGE_SIZE,
            1, new PaletteData(new RGB[] { new RGB(191, 191, 191) })));

    @Override
    public Image getImage(Object element) {
        if (element instanceof ResoluteProofNode) {
            ResoluteProofNode node = (ResoluteProofNode) element;
            ResoluteValue value = node.getRetVal();

            if (value.isBool()) {
                if (value.getBool()) {
                    return TRUE_IMAGE;
                } else {
                    return FALSE_IMAGE;
                }
            }

            if (value.isFail()) {
                return FAIL_IMAGE;
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
