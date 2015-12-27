package org.powerbot.script.rt4;

import java.util.ArrayList;
import java.util.List;

import org.powerbot.bot.rt4.client.Client;

public class Npcs extends BasicQuery<Npc> {
	public Npcs(final ClientContext ctx) {
		super(ctx);
	}

	@Override
	public List<Npc> get() {
		final List<Npc> r = new ArrayList<Npc>();
		final Client client = ctx.client();
		if (client == null) {
			return r;
		}
		final int[] indices = client.getNpcIndices();
		final org.powerbot.bot.rt4.client.Npc[] npcs = client.getNpcs();
		if (indices == null || npcs == null) {
			return r;
		}
		for (final int k : indices) {
			final org.powerbot.bot.rt4.client.Npc n = npcs[k];
			if (!n.isNull()) {
				r.add(new Npc(ctx, n));
			}
		}
		return r;
	}

	@Override
	public Npc nil() {
		return new Npc(ctx, null);
	}
}
