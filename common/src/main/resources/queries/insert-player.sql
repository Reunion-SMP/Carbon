INSERT{!PSQL: IGNORE} INTO carbon_users(
    id,
    muted,
    muteexpiration,
    deafened,
    selectedchannel,
    displayname,
    lastwhispertarget,
    whisperreplytarget,
    spying,
    ignoringdms,
    party,
    applycustomfilters,
    nochatformat
) VALUES (
    :id,
    :muted,
    :muteexpiration,
    :deafened,
    :selectedchannel,
    :displayname,
    :lastwhispertarget,
    :whisperreplytarget,
    :spying,
    :ignoringdms,
    :party,
    :applycustomfilters,
    :nochatformat
){PSQL: ON CONFLICT DO NOTHING};
