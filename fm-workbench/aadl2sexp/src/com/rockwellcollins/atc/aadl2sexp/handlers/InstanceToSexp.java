package com.rockwellcollins.atc.aadl2sexp.handlers;

import java.util.ArrayList;
import java.util.List;

import org.osate.aadl2.ComponentImplementation;
import org.osate.aadl2.ConnectedElement;
import org.osate.aadl2.Connection;
import org.osate.aadl2.instance.ComponentInstance;
import org.osate.aadl2.instance.FeatureInstance;

public class InstanceToSexp {
    public static Sexp convert(ComponentInstance ci) {
        List<Sexp> args = new ArrayList<Sexp>();
        args.add(name(ci));
        args.add(classifier(ci));
        args.add(features(ci));
        args.add(subcomponents(ci));

        if (ci.getComponentClassifier() instanceof ComponentImplementation) {
            ComponentImplementation impl = (ComponentImplementation) ci.getComponentClassifier();
            args.add(connections(impl));
        } else {
            args.add(new Cons("connections"));
        }

        return new Cons("ComponentInstance", args);
    }

    private static Sexp name(ComponentInstance ci) {
        return new Cons("name", new Symbol(ci.getName()));
    }

    private static Sexp classifier(ComponentInstance ci) {
        return new Cons("classifier", new Symbol(ci.getComponentClassifier().getName()));
    }

    private static Sexp convert(FeatureInstance fi) {
        List<Sexp> args = new ArrayList<Sexp>();
        args.add(new Symbol(fi.getName()));
        args.add(direction(fi));
        args.add(type(fi));
        return new Cons("feature", args);
    }

    private static Sexp direction(FeatureInstance fi) {
        return new Cons("direction", new Symbol(fi.getDirection().toString()));
    }

    private static Sexp type(FeatureInstance fi) {
        return new Cons("classifier", new Symbol(fi.getFeature().getClassifier().getName()));
    }

    private static Sexp features(ComponentInstance ci) {
        List<Sexp> args = new ArrayList<Sexp>();
        for (FeatureInstance fi : ci.getFeatureInstances()) {
            args.add(convert(fi));
        }
        return new Cons("features", args);
    }

    private static Sexp subcomponents(ComponentInstance ci) {
        List<Sexp> args = new ArrayList<Sexp>();
        for (ComponentInstance sub : ci.getComponentInstances()) {
            args.add(convert(sub));
        }
        return new Cons("subcomponents", args);
    }

    private static Sexp connections(ComponentImplementation ci) {
        List<Sexp> args = new ArrayList<Sexp>();
        for (Connection conn : ci.getOwnedConnections()) {
            args.add(convert(conn));
        }
        return new Cons("connections", args);
    }

    private static Sexp convert(Connection conn) {
        List<Sexp> args = new ArrayList<Sexp>();
        args.add(new Symbol(conn.getFullName()));
        args.add(new Symbol(getName(conn.getSource())));
        args.add(new Symbol(getName(conn.getDestination())));
        return new Cons("connection", args);
    }

    private static String getName(ConnectedElement ce) {
        String prefix = "";
        if (ce.getContext() != null) {
            prefix = ce.getContext().getName() + ".";
        }
        return prefix + ce.getConnectionEnd().getName();
    }
}
