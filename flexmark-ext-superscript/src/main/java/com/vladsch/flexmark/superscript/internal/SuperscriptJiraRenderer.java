package com.vladsch.flexmark.superscript.internal;

import com.vladsch.flexmark.html.HtmlWriter;
import com.vladsch.flexmark.html.renderer.NodeRenderer;
import com.vladsch.flexmark.html.renderer.NodeRendererContext;
import com.vladsch.flexmark.html.renderer.NodeRendererFactory;
import com.vladsch.flexmark.html.renderer.NodeRenderingHandler;
import com.vladsch.flexmark.superscript.Superscript;
import com.vladsch.flexmark.util.data.DataHolder;

import java.util.HashSet;
import java.util.Set;

public class SuperscriptJiraRenderer implements NodeRenderer {
    public SuperscriptJiraRenderer(DataHolder options) {

    }

    @Override
    public Set<NodeRenderingHandler<?>> getNodeRenderingHandlers() {
        Set<NodeRenderingHandler<?>> set = new HashSet<>();
        set.add(new NodeRenderingHandler<>(Superscript.class, this::render));
        return set;
    }

    private void render(Superscript node, NodeRendererContext context, HtmlWriter html) {
        html.raw("^");
        context.renderChildren(node);
        html.raw("^");
    }

    public static class Factory implements NodeRendererFactory {
        @Override
        public NodeRenderer apply(DataHolder options) {
            return new SuperscriptJiraRenderer(options);
        }
    }
}
