import sys
from collections import namedtuple
from ways import load_map_from_csv, compute_distance

Node = namedtuple('Node', ['junction', 'parent', 'g_value', 'h_value', 'f_value', ])

roads = None


def get_best_node(node_list):
    min_val = sys.maxsize
    best_node = None
    for current in node_list:
        if current.f_value < min_val:
            best_node = current
            min_val = current.f_value
    return best_node


def children_of_junction(junction):
    children = list()
    for lnk in junction.links:
        if lnk.source == junction.index:
            children.append(roads.junctions()[lnk.target])
    return children


def list_find_node(node_list, junction):
    for x in node_list:
        if x.junction == junction:
            return x
    return None


def price_function(source_junction, target_junction, t0):
    for lnk in source_junction.links:
        if lnk.target == target_junction.index:
            return roads.realtime_link_speed(lnk, t0)
    return sys.maxsize


def heuristic_function(source_junction, target_junction, t0):
    return compute_distance(source_junction.lat, source_junction.lon, target_junction.lat, target_junction.lon)


def format_result(last_node):
    top = last_node
    all_indices = list()
    while top is not None:
        all_indices.append(top.junction.index)
        top = top.parent
    return all_indices


def run_astar(source_junction_index, target_junction_index, t0, price_func=price_function,
              heuristic_func=heuristic_function):
    global roads
    roads = load_map_from_csv()
    source_junction = roads.junctions()[source_junction_index]
    target_junction = roads.junctions()[target_junction_index]
    closed_list = list()
    open_list = list()

    source_h_value = heuristic_func(source_junction, target_junction, t0)
    open_list.append(Node(source_junction, None, 0, source_h_value, source_h_value))

    while open_list:  # while list is not empty
        best_node = get_best_node(open_list)
        open_list.remove(best_node)
        closed_list.append(best_node)
        if best_node.junction == target_junction:
            return format_result(best_node)
        for son in children_of_junction(best_node.junction):
            son_g_value = price_func(best_node.junction, son, t0) + best_node.g_value
            son_in_open = list_find_node(open_list, son)
            if son_in_open is not None:
                if son_in_open.g_value > son_g_value:
                    son_copy = Node(son_in_open.junction, best_node, son_g_value,
                                    heuristic_func(son_in_open.junction, target_junction, t0),
                                    heuristic_func(son_in_open.junction, target_junction, t0) + son_g_value)
                    open_list.remove(son_in_open)
                    open_list.append(son_copy)
                    # son_in_open.g_value = son_g_value
                    # son_in_open.h_value = heuristic_func(son_in_open.junction, target_junction, t0)
                    # son_in_open.f_value = son_in_open.g_value + son_in_open.h_value
                    # son_in_open.parent = best_node
                continue
            son_in_closed = list_find_node(closed_list, son)
            if son_in_closed is not None:
                if son_in_closed.g_value > son_g_value:
                    son_copy = Node(son_in_closed.junction, best_node, son_g_value,
                                    heuristic_func(son_in_open.junction, target_junction, t0),
                                    heuristic_func(son_in_open.junction, target_junction, t0) + son_g_value)
                    open_list.remove(son_in_closed)
                    open_list.append(son_copy)
                    # closed_list.remove(son_in_closed)
                    # son_in_closed.g_value = son_g_value
                    # son_in_closed.h_value = heuristic_func(son_in_open.junction, target_junction, t0)
                    # son_in_closed.f_value = son_in_open.g_value + son_in_open.h_value
                    # son_in_closed.parent = best_node
                    # open_list.append(son_in_closed)
                continue
            son_h_value = heuristic_func(son, target_junction, t0)
            open_list.append(Node(son, best_node, son_g_value, son_h_value, son_g_value + son_h_value))
    return None
